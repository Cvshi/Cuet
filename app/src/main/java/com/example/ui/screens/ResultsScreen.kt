package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Question
import com.example.data.repository.StreakInfo
import com.example.ui.components.ExplanationCard
import com.example.ui.theme.GeoCyanLight
import com.example.ui.theme.GeoErrorRed
import com.example.ui.theme.GeoFlameOrange
import com.example.ui.theme.GeoNavyDark
import com.example.ui.theme.GeoSuccessGreen

@Composable
fun ResultsScreen(
    title: String,
    score: Int,
    total: Int,
    timeSeconds: Int,
    questions: List<Question>,
    userAnswers: Map<Int, Int>,
    streakInfo: StreakInfo,
    onRetake: () -> Unit,
    onHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    val percentage = if (total > 0) ((score.toDouble() / total) * 100).toInt() else 0
    val minutes = timeSeconds / 60
    val seconds = timeSeconds % 60
    val timeFormatted = String.format("%02d:%02d", minutes, seconds)

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = GeoNavyDark,
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF0C1426))
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = onRetake,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .weight(1f)
                            .testTag("retake_quiz_button")
                    ) {
                        Icon(imageVector = Icons.Default.Replay, contentDescription = null)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Retake")
                    }

                    Button(
                        onClick = onHome,
                        colors = ButtonDefaults.buttonColors(containerColor = GeoFlameOrange),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .weight(1f)
                            .testTag("home_button")
                    ) {
                        Icon(imageVector = Icons.Default.Home, contentDescription = null, tint = Color.White)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Dashboard", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }

            // Streak Milestone Banner
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.horizontalGradient(
                                listOf(Color(0xFF7C2D12), Color(0xFF162544))
                            ),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .border(1.dp, GeoFlameOrange.copy(alpha = 0.6f), RoundedCornerShape(20.dp))
                        .padding(16.dp)
                        .testTag("streak_celebration_banner")
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(GeoFlameOrange, shape = CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocalFireDepartment,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(30.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column {
                            Text(
                                text = "Daily Streak Active! 🔥",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = "${streakInfo.currentStreak} Day Continuous Streak • Best: ${streakInfo.maxStreak} Days",
                                fontSize = 12.sp,
                                color = Color(0xFFFFDBCF)
                            )
                        }
                    }
                }
            }

            // Score Summary Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF131D31))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = title,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = GeoCyanLight
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(
                                text = "$score",
                                fontSize = 48.sp,
                                fontWeight = FontWeight.Black,
                                color = if (percentage >= 70) GeoSuccessGreen else GeoFlameOrange
                            )
                            Text(
                                text = " / $total",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF94A3B8),
                                modifier = Modifier.padding(bottom = 6.dp)
                            )
                        }

                        Text(
                            text = "$percentage% Accuracy • Time: $timeFormatted",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFFCBD5E1)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = when {
                                percentage >= 80 -> "🌟 Excellent! Solid mastery of CUET Geophysics principles."
                                percentage >= 50 -> "👍 Good effort! Review the detailed explanations below to improve."
                                else -> "📚 Keep practicing! Read the physics derivations below to strengthen your fundamentals."
                            },
                            fontSize = 12.sp,
                            color = Color(0xFF94A3B8),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    }
                }
            }

            // Section Header: Detailed Review & Explanations
            item {
                Text(
                    text = "Question Review & Explanations",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            // List of questions with answers and proper explanations
            items(questions.size) { index ->
                val question = questions[index]
                val userAnswerIndex = userAnswers[question.id]
                val isCorrect = userAnswerIndex == question.correctIndex
                val optionLetters = listOf("(a)", "(b)", "(c)", "(d)")

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF131D31))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(26.dp)
                                    .background(
                                        if (isCorrect) GeoSuccessGreen else GeoErrorRed,
                                        shape = CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = if (isCorrect) Icons.Default.Check else Icons.Default.Close,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Q${index + 1}. ${question.topic}",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isCorrect) GeoSuccessGreen else GeoErrorRed
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = question.questionText,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // User answer vs correct answer
                        Row {
                            Text(
                                text = "Your answer: ",
                                fontSize = 12.sp,
                                color = Color(0xFF94A3B8)
                            )
                            Text(
                                text = if (userAnswerIndex != null)
                                    "${optionLetters.getOrElse(userAnswerIndex) { "" }} ${question.options.getOrElse(userAnswerIndex) { "" }}"
                                else
                                    "Not answered",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isCorrect) GeoSuccessGreen else GeoErrorRed
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Full proper explanation
                        ExplanationCard(
                            correctOptionLetter = question.correctOptionLetter,
                            explanationText = question.explanation,
                            formulaTakeaway = question.formulaTakeaway
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}
