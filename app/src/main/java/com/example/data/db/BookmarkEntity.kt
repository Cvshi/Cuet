package com.example.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarked_questions")
data class BookmarkEntity(
    @PrimaryKey val questionId: Int,
    val timestamp: Long = System.currentTimeMillis()
)
