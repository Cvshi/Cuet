package com.example.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_streak")
data class StreakEntity(
    @PrimaryKey val id: Int = 1,
    val currentStreak: Int = 0,
    val maxStreak: Int = 0,
    val lastActiveEpochDay: Long = 0L,
    val quizzesCompletedToday: Int = 0,
    val totalQuizzesCompleted: Int = 0,
    val totalQuestionsAnswered: Int = 0,
    val totalCorrectAnswers: Int = 0,
    val activeDaysHistory: String = "" // Comma-separated epoch days e.g. "19940,19941"
)
