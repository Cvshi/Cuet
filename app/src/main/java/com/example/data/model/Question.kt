package com.example.data.model

data class Question(
    val id: Int,
    val chapter: Int,
    val chapterTitle: String,
    val topic: String,
    val questionText: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String,
    val formulaTakeaway: String = ""
) {
    val correctOptionLetter: String
        get() = when (correctIndex) {
            0 -> "(a)"
            1 -> "(b)"
            2 -> "(c)"
            3 -> "(d)"
            else -> ""
        }
}
