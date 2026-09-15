package com.example.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_attempts")
data class QuizAttemptEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val timestamp: Long = System.currentTimeMillis(),
    val quizTitle: String,
    val chapterId: Int, // 0 for mixed/daily, 1, 2, 3
    val score: Int,
    val totalQuestions: Int,
    val timeTakenSeconds: Int
)
