package com.example

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.receiver.StreakAppWidgetProvider
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.QuizScreen
import com.example.ui.screens.ResultsScreen
import com.example.ui.screens.StatsScreen
import com.example.ui.screens.StudyNotesScreen
import com.example.ui.theme.GeoNavyDark
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.QuizViewModel
import com.example.ui.viewmodel.Screen

class MainActivity : ComponentActivity() {

    private val viewModel: QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        handleWidgetIntent(intent)

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = GeoNavyDark
                ) {
                    AppNavigation(viewModel)
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleWidgetIntent(intent)
    }

    private fun handleWidgetIntent(intent: Intent?) {
        if (intent?.getBooleanExtra(StreakAppWidgetProvider.EXTRA_START_DAILY_QUIZ, false) == true) {
            viewModel.startDailyQuiz()
        }
    }
}

@Composable
fun AppNavigation(viewModel: QuizViewModel) {
    val currentScreen by viewModel.currentScreen.collectAsStateWithLifecycle()
    val streakInfo by viewModel.streakInfo.collectAsStateWithLifecycle()
    val quizUiState by viewModel.quizUiState.collectAsStateWithLifecycle()
    val bookmarkedIds by viewModel.bookmarkedIds.collectAsStateWithLifecycle()
    val attempts by viewModel.attempts.collectAsStateWithLifecycle()

    when (val screen = currentScreen) {
        is Screen.Home -> {
            HomeScreen(
                streakInfo = streakInfo,
                onStartDailyQuiz = { viewModel.startDailyQuiz() },
                onStartChapter = { chapterId -> viewModel.startChapterQuiz(chapterId) },
                onStartFullMock = { viewModel.startFullMock() },
                onOpenStudyNotes = { viewModel.navigateTo(Screen.StudyNotes) },
                onOpenStats = { viewModel.navigateTo(Screen.Stats) }
            )
        }

        is Screen.Quiz -> {
            BackHandler {
                viewModel.navigateTo(Screen.Home)
            }
            QuizScreen(
                title = screen.title,
                chapterId = screen.chapterId,
                uiState = quizUiState,
                bookmarkedIds = bookmarkedIds,
                onSelectOption = { qId, optIdx -> viewModel.selectOption(qId, optIdx) },
                onNext = { viewModel.goToNextQuestion() },
                onPrevious = { viewModel.goToPreviousQuestion() },
                onSubmit = { viewModel.submitQuiz(screen.title, screen.chapterId) },
                onToggleBookmark = { qId -> viewModel.toggleBookmark(qId) },
                onExit = { viewModel.navigateTo(Screen.Home) }
            )
        }

        is Screen.Results -> {
            BackHandler {
                viewModel.navigateTo(Screen.Home)
            }
            ResultsScreen(
                title = screen.title,
                score = screen.score,
                total = screen.total,
                timeSeconds = screen.timeSeconds,
                questions = screen.questions,
                userAnswers = screen.userAnswers,
                streakInfo = streakInfo,
                onRetake = {
                    if (screen.chapterId == 0) {
                        viewModel.startDailyQuiz()
                    } else {
                        viewModel.startChapterQuiz(screen.chapterId)
                    }
                },
                onHome = { viewModel.navigateTo(Screen.Home) }
            )
        }

        is Screen.StudyNotes -> {
            BackHandler {
                viewModel.navigateTo(Screen.Home)
            }
            StudyNotesScreen(
                bookmarkedIds = bookmarkedIds,
                onToggleBookmark = { qId -> viewModel.toggleBookmark(qId) },
                onBack = { viewModel.navigateTo(Screen.Home) }
            )
        }

        is Screen.Stats -> {
            BackHandler {
                viewModel.navigateTo(Screen.Home)
            }
            StatsScreen(
                streakInfo = streakInfo,
                attempts = attempts,
                onBack = { viewModel.navigateTo(Screen.Home) }
            )
        }
    }
}
