package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.db.AppDatabase
import com.example.data.model.GeophysicsQuestionBank
import com.example.data.model.Question
import com.example.data.repository.StreakInfo
import com.example.data.repository.StreakRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed class Screen {
    object Home : Screen()
    data class Quiz(val title: String, val chapterId: Int) : Screen()
    data class Results(
        val title: String,
        val chapterId: Int,
        val score: Int,
        val total: Int,
        val timeSeconds: Int,
        val questions: List<Question>,
        val userAnswers: Map<Int, Int> // questionId -> selectedIndex
    ) : Screen()
    object StudyNotes : Screen()
    object Stats : Screen()
}

data class QuizUiState(
    val questions: List<Question> = emptyList(),
    val currentIndex: Int = 0,
    val selectedAnswers: Map<Int, Int> = emptyMap(), // questionId -> optionIndex
    val isRevealed: Map<Int, Boolean> = emptyMap(),  // questionId -> true if explanation revealed
    val isExamMode: Boolean = false,
    val isFinished: Boolean = false,
    val timeSeconds: Int = 0
)

class QuizViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val streakRepository = StreakRepository(db.appDao(), application)

    val streakInfo: StateFlow<StreakInfo> = streakRepository.streakInfoFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = StreakInfo()
        )

    val bookmarkedIds: StateFlow<List<Int>> = streakRepository.bookmarkedIdsFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val attempts = streakRepository.attemptsFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _currentScreen = MutableStateFlow<Screen>(Screen.Home)
    val currentScreen: StateFlow<Screen> = _currentScreen.asStateFlow()

    private val _quizUiState = MutableStateFlow(QuizUiState())
    val quizUiState: StateFlow<QuizUiState> = _quizUiState.asStateFlow()

    private var timerJob: Job? = null

    fun navigateTo(screen: Screen) {
        _currentScreen.value = screen
    }

    fun startDailyQuiz() {
        val dailyQuestions = GeophysicsQuestionBank.getDailyQuizQuestions(count = 5)
        startQuizSession(
            questions = dailyQuestions,
            title = "Daily Streak Challenge",
            chapterId = 0,
            isExamMode = false
        )
    }

    fun startChapterQuiz(chapter: Int) {
        val chapterQuestions = GeophysicsQuestionBank.getQuestionsByChapter(chapter)
        val title = when (chapter) {
            1 -> "Ch 1: Classical Mechanics"
            2 -> "Ch 2: Conservation Laws"
            3 -> "Ch 3: Rotational Dynamics"
            else -> "Chapter $chapter Quiz"
        }
        startQuizSession(
            questions = chapterQuestions,
            title = title,
            chapterId = chapter,
            isExamMode = false
        )
    }

    fun startFullMock() {
        val mockQuestions = GeophysicsQuestionBank.getFullMockQuestions(count = 15)
        startQuizSession(
            questions = mockQuestions,
            title = "Full CUET Geophysics Mock",
            chapterId = 0,
            isExamMode = true
        )
    }

    private fun startQuizSession(
        questions: List<Question>,
        title: String,
        chapterId: Int,
        isExamMode: Boolean
    ) {
        timerJob?.cancel()
        _quizUiState.value = QuizUiState(
            questions = questions,
            currentIndex = 0,
            selectedAnswers = emptyMap(),
            isRevealed = emptyMap(),
            isExamMode = isExamMode,
            isFinished = false,
            timeSeconds = 0
        )
        _currentScreen.value = Screen.Quiz(title, chapterId)

        timerJob = viewModelScope.launch {
            while (true) {
                delay(1000)
                _quizUiState.value = _quizUiState.value.let {
                    it.copy(timeSeconds = it.timeSeconds + 1)
                }
            }
        }
    }

    fun selectOption(questionId: Int, optionIndex: Int) {
        val currentState = _quizUiState.value
        // If already selected in practice mode, don't re-select
        if (!currentState.isExamMode && currentState.selectedAnswers.containsKey(questionId)) {
            return
        }

        val updatedAnswers = currentState.selectedAnswers.toMutableMap()
        updatedAnswers[questionId] = optionIndex

        val updatedRevealed = currentState.isRevealed.toMutableMap()
        if (!currentState.isExamMode) {
            updatedRevealed[questionId] = true
        }

        _quizUiState.value = currentState.copy(
            selectedAnswers = updatedAnswers,
            isRevealed = updatedRevealed
        )
    }

    fun revealExplanation(questionId: Int) {
        val updatedRevealed = _quizUiState.value.isRevealed.toMutableMap()
        updatedRevealed[questionId] = true
        _quizUiState.value = _quizUiState.value.copy(isRevealed = updatedRevealed)
    }

    fun goToNextQuestion() {
        val currentState = _quizUiState.value
        if (currentState.currentIndex < currentState.questions.size - 1) {
            _quizUiState.value = currentState.copy(currentIndex = currentState.currentIndex + 1)
        }
    }

    fun goToPreviousQuestion() {
        val currentState = _quizUiState.value
        if (currentState.currentIndex > 0) {
            _quizUiState.value = currentState.copy(currentIndex = currentState.currentIndex - 1)
        }
    }

    fun submitQuiz(quizTitle: String, chapterId: Int) {
        timerJob?.cancel()
        val state = _quizUiState.value
        var score = 0
        state.questions.forEach { q ->
            if (state.selectedAnswers[q.id] == q.correctIndex) {
                score++
            }
        }

        viewModelScope.launch {
            streakRepository.recordQuizCompletion(
                quizTitle = quizTitle,
                chapterId = chapterId,
                score = score,
                totalQuestions = state.questions.size,
                timeTakenSeconds = state.timeSeconds
            )
        }

        _currentScreen.value = Screen.Results(
            title = quizTitle,
            chapterId = chapterId,
            score = score,
            total = state.questions.size,
            timeSeconds = state.timeSeconds,
            questions = state.questions,
            userAnswers = state.selectedAnswers
        )
    }

    fun toggleBookmark(questionId: Int) {
        viewModelScope.launch {
            val isCurrent = bookmarkedIds.value.contains(questionId)
            streakRepository.toggleBookmark(questionId, isCurrent)
        }
    }
}
