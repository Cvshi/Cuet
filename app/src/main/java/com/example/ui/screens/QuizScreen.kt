package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Question
import com.example.ui.components.ExplanationCard
import com.example.ui.components.OptionButton
import com.example.ui.theme.GeoAmberGold
import com.example.ui.theme.GeoCyan
import com.example.ui.theme.GeoCyanLight
import com.example.ui.theme.GeoFlameOrange
import com.example.ui.theme.GeoNavyDark
import com.example.ui.viewmodel.QuizUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    title: String,
    chapterId: Int,
    uiState: QuizUiState,
    bookmarkedIds: List<Int>,
    onSelectOption: (questionId: Int, optionIndex: Int) -> Unit,
    onNext: () -> Unit,
    onPrevious: () -> Unit,
    onSubmit: () -> Unit,
    onToggleBookmark: (questionId: Int) -> Unit,
    onExit: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currentQuestion: Question? = uiState.questions.getOrNull(uiState.currentIndex)
    val isLastQuestion = uiState.currentIndex == uiState.questions.size - 1
    val isBookmarked by remember(currentQuestion, bookmarkedIds) {
        derivedStateOf {
            currentQuestion?.let { bookmarkedIds.contains(it.id) } ?: false
        }
    }

    val minutes = uiState.timeSeconds / 60
    val seconds = uiState.timeSeconds % 60
    val timeFormatted = String.format("%02d:%02d", minutes, seconds)

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = GeoNavyDark,
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = title,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "Question ${uiState.currentIndex + 1} of ${uiState.questions.size}",
                            fontSize = 11.sp,
                            color = GeoCyanLight
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onExit,
                        modifier = Modifier.testTag("quiz_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Exit Quiz",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    // Timer Pill
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(Color(0xFF1E293B), RoundedCornerShape(12.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Timer,
                            contentDescription = null,
                            tint = GeoCyanLight,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = timeFormatted,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                    // Bookmark toggle
                    currentQuestion?.let { q ->
                        IconButton(
                            onClick = { onToggleBookmark(q.id) },
                            modifier = Modifier.testTag("bookmark_button")
                        ) {
                            Icon(
                                imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                                contentDescription = if (isBookmarked) "Bookmarked" else "Bookmark",
                                tint = if (isBookmarked) GeoAmberGold else Color(0xFF94A3B8)
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = GeoNavyDark
                )
            )
        },
        bottomBar = {
            // Navigation bottom bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF0C1426))
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        onClick = onPrevious,
                        enabled = uiState.currentIndex > 0,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.testTag("previous_question_button")
                    ) {
                        Icon(imageVector = Icons.Default.ChevronLeft, contentDescription = null)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Prev")
                    }

                    if (isLastQuestion) {
                        Button(
                            onClick = onSubmit,
                            colors = ButtonDefaults.buttonColors(containerColor = GeoFlameOrange),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.testTag("submit_quiz_button")
                        ) {
                            Text("Submit & Finish", fontWeight = FontWeight.Bold, color = Color.White)
                        }
                    } else {
                        Button(
                            onClick = onNext,
                            colors = ButtonDefaults.buttonColors(containerColor = GeoCyan),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.testTag("next_question_button")
                        ) {
                            Text("Next", fontWeight = FontWeight.Bold, color = Color.White)
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null)
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        if (currentQuestion == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Loading question...", color = Color.White)
            }
        } else {
            val isAnswered = uiState.selectedAnswers.containsKey(currentQuestion.id)
            val selectedOption = uiState.selectedAnswers[currentQuestion.id]
            val isRevealed = uiState.isRevealed[currentQuestion.id] == true

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Progress Bar
                item {
                    val progress = (uiState.currentIndex + 1).toFloat() / uiState.questions.size
                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp),
                        color = GeoCyanLight,
                        trackColor = Color(0xFF1E293B)
                    )
                }

                // Question Card
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("question_card"),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF131F37))
                    ) {
                        Column(modifier = Modifier.padding(18.dp)) {
                            // Topic chip
                            Box(
                                modifier = Modifier
                                    .background(GeoCyan.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = currentQuestion.topic,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = GeoCyanLight
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = currentQuestion.questionText,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                lineHeight = 24.sp,
                                color = Color.White
                            )
                        }
                    }
                }

                // Options list
                item {
                    Text(
                        text = "Choose your answer:",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF94A3B8)
                    )
                }

                val optionLetters = listOf("(a)", "(b)", "(c)", "(d)")
                items(currentQuestion.options.size) { index ->
                    val optionText = currentQuestion.options[index]
                    val isSelected = selectedOption == index
                    val isCorrectOption = index == currentQuestion.correctIndex

                    OptionButton(
                        optionLetter = optionLetters.getOrElse(index) { "($index)" },
                        optionText = optionText,
                        isSelected = isSelected,
                        isCorrect = isCorrectOption,
                        isRevealed = isRevealed,
                        onClick = {
                            onSelectOption(currentQuestion.id, index)
                        }
                    )
                }

                // Proper In-depth Explanation Card (appears as soon as answered or revealed)
                if (isRevealed) {
                    item {
                        ExplanationCard(
                            correctOptionLetter = currentQuestion.correctOptionLetter,
                            explanationText = currentQuestion.explanation,
                            formulaTakeaway = currentQuestion.formulaTakeaway
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}
