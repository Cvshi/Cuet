package com.example.data.repository

import android.content.Context
import com.example.data.db.AppDao
import com.example.data.db.BookmarkEntity
import com.example.data.db.QuizAttemptEntity
import com.example.data.db.StreakEntity
import com.example.receiver.StreakAppWidgetProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

data class StreakInfo(
    val currentStreak: Int = 0,
    val maxStreak: Int = 0,
    val isPracticedToday: Boolean = false,
    val quizzesCompletedToday: Int = 0,
    val totalQuizzesCompleted: Int = 0,
    val totalQuestionsAnswered: Int = 0,
    val totalCorrectAnswers: Int = 0,
    val activeDaysHistory: Set<Long> = emptySet(),
    val accuracyPercentage: Int = 0
)

class StreakRepository(
    private val appDao: AppDao,
    private val context: Context
) {
    private val appContext = context.applicationContext

    val streakInfoFlow: Flow<StreakInfo> = appDao.getStreak().map { entity ->
        val todayEpoch = LocalDate.now().toEpochDay()
        if (entity == null) {
            StreakInfo()
        } else {
            val diff = todayEpoch - entity.lastActiveEpochDay
            val isPracticedToday = diff == 0L && entity.quizzesCompletedToday > 0
            val effectiveCurrentStreak = when {
                diff == 0L -> entity.currentStreak
                diff == 1L -> entity.currentStreak // Can be continued today
                else -> 0 // Broken streak
            }
            val accuracy = if (entity.totalQuestionsAnswered > 0) {
                ((entity.totalCorrectAnswers.toDouble() / entity.totalQuestionsAnswered) * 100).toInt()
            } else 0

            val historySet = entity.activeDaysHistory
                .split(",")
                .mapNotNull { it.trim().toLongOrNull() }
                .toSet()

            StreakInfo(
                currentStreak = effectiveCurrentStreak,
                maxStreak = max(effectiveCurrentStreak, entity.maxStreak),
                isPracticedToday = isPracticedToday,
                quizzesCompletedToday = if (diff == 0L) entity.quizzesCompletedToday else 0,
                totalQuizzesCompleted = entity.totalQuizzesCompleted,
                totalQuestionsAnswered = entity.totalQuestionsAnswered,
                totalCorrectAnswers = entity.totalCorrectAnswers,
                activeDaysHistory = historySet,
                accuracyPercentage = accuracy
            )
        }
    }

    val attemptsFlow: Flow<List<QuizAttemptEntity>> = appDao.getAllAttempts()
    val bookmarkedIdsFlow: Flow<List<Int>> = appDao.getBookmarkedQuestionIds()

    suspend fun recordQuizCompletion(
        quizTitle: String,
        chapterId: Int,
        score: Int,
        totalQuestions: Int,
        timeTakenSeconds: Int
    ): StreakInfo {
        val todayEpoch = LocalDate.now().toEpochDay()
        val current = appDao.getStreakSync()

        val newCurrentStreak: Int
        val newMaxStreak: Int
        val newQuizzesToday: Int
        val historySet: MutableSet<Long> = current?.activeDaysHistory
            ?.split(",")
            ?.mapNotNull { it.trim().toLongOrNull() }
            ?.toMutableSet() ?: mutableSetOf()

        historySet.add(todayEpoch)

        if (current == null) {
            newCurrentStreak = 1
            newMaxStreak = 1
            newQuizzesToday = 1
        } else {
            val diff = todayEpoch - current.lastActiveEpochDay
            when {
                diff == 0L -> {
                    // Already practiced today, keep streak, increase count
                    newCurrentStreak = if (current.currentStreak == 0) 1 else current.currentStreak
                    newMaxStreak = max(newCurrentStreak, current.maxStreak)
                    newQuizzesToday = current.quizzesCompletedToday + 1
                }
                diff == 1L -> {
                    // Consecutive day streak!
                    newCurrentStreak = current.currentStreak + 1
                    newMaxStreak = max(newCurrentStreak, current.maxStreak)
                    newQuizzesToday = 1
                }
                else -> {
                    // Streak was broken, restart
                    newCurrentStreak = 1
                    newMaxStreak = max(newCurrentStreak, current.maxStreak)
                    newQuizzesToday = 1
                }
            }
        }

        val updatedEntity = StreakEntity(
            id = 1,
            currentStreak = newCurrentStreak,
            maxStreak = newMaxStreak,
            lastActiveEpochDay = todayEpoch,
            quizzesCompletedToday = newQuizzesToday,
            totalQuizzesCompleted = (current?.totalQuizzesCompleted ?: 0) + 1,
            totalQuestionsAnswered = (current?.totalQuestionsAnswered ?: 0) + totalQuestions,
            totalCorrectAnswers = (current?.totalCorrectAnswers ?: 0) + score,
            activeDaysHistory = historySet.joinToString(",")
        )

        appDao.saveStreak(updatedEntity)

        // Save attempt
        val attempt = QuizAttemptEntity(
            quizTitle = quizTitle,
            chapterId = chapterId,
            score = score,
            totalQuestions = totalQuestions,
            timeTakenSeconds = timeTakenSeconds
        )
        appDao.insertAttempt(attempt)

        // Broadcast to home screen widget
        StreakAppWidgetProvider.updateAllWidgets(appContext, newCurrentStreak, true)

        val accuracy = if (updatedEntity.totalQuestionsAnswered > 0) {
            ((updatedEntity.totalCorrectAnswers.toDouble() / updatedEntity.totalQuestionsAnswered) * 100).toInt()
        } else 0

        return StreakInfo(
            currentStreak = newCurrentStreak,
            maxStreak = newMaxStreak,
            isPracticedToday = true,
            quizzesCompletedToday = newQuizzesToday,
            totalQuizzesCompleted = updatedEntity.totalQuizzesCompleted,
            totalQuestionsAnswered = updatedEntity.totalQuestionsAnswered,
            totalCorrectAnswers = updatedEntity.totalCorrectAnswers,
            activeDaysHistory = historySet,
            accuracyPercentage = accuracy
        )
    }

    suspend fun toggleBookmark(questionId: Int, isBookmarked: Boolean) {
        if (isBookmarked) {
            appDao.removeBookmark(questionId)
        } else {
            appDao.bookmarkQuestion(BookmarkEntity(questionId))
        }
    }

    private fun max(a: Int, b: Int): Int = if (a > b) a else b
}
