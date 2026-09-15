package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Query("SELECT * FROM user_streak WHERE id = 1")
    fun getStreak(): Flow<StreakEntity?>

    @Query("SELECT * FROM user_streak WHERE id = 1")
    suspend fun getStreakSync(): StreakEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveStreak(streak: StreakEntity)

    @Query("SELECT * FROM quiz_attempts ORDER BY timestamp DESC")
    fun getAllAttempts(): Flow<List<QuizAttemptEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttempt(attempt: QuizAttemptEntity)

    @Query("SELECT questionId FROM bookmarked_questions")
    fun getBookmarkedQuestionIds(): Flow<List<Int>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bookmarkQuestion(bookmark: BookmarkEntity)

    @Query("DELETE FROM bookmarked_questions WHERE questionId = :questionId")
    suspend fun removeBookmark(questionId: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarked_questions WHERE questionId = :questionId)")
    suspend fun isBookmarked(questionId: Int): Boolean
}
