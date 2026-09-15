package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.repository.StreakInfo
import com.example.ui.theme.GeoAmberGold
import com.example.ui.theme.GeoCyanLight
import com.example.ui.theme.GeoFlameOrange
import com.example.ui.theme.GeoSuccessGreen
import java.time.DayOfWeek
import java.time.LocalDate

@Composable
fun StreakWidgetCard(
    streakInfo: StreakInfo,
    onStartDailyQuiz: () -> Unit,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "flame_pulse")
    val flameScale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.15f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "flame_scale"
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("in_screen_streak_widget"),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF131F37)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF162544),
                            Color(0xFF0F172A)
                        )
                    )
                )
                .border(
                    width = 1.dp,
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            GeoFlameOrange.copy(alpha = 0.5f),
                            GeoCyanLight.copy(alpha = 0.3f)
                        )
                    ),
                    shape = RoundedCornerShape(24.dp)
                )
                .padding(20.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                // Header: Badge & Status
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .background(
                                    Brush.radialGradient(
                                        colors = listOf(
                                            GeoFlameOrange.copy(alpha = 0.35f),
                                            Color.Transparent
                                        )
                                    ),
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocalFireDepartment,
                                contentDescription = "Daily Streak Fire",
                                tint = if (streakInfo.currentStreak > 0) GeoFlameOrange else Color.Gray,
                                modifier = Modifier
                                    .size(32.dp)
                                    .scale(if (streakInfo.currentStreak > 0) flameScale else 1.0f)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Row(verticalAlignment = Alignment.Bottom) {
                                Text(
                                    text = "${streakInfo.currentStreak}",
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Black,
                                    color = Color.White
                                )
                                Text(
                                    text = if (streakInfo.currentStreak == 1) " DAY STREAK" else " DAYS STREAK",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = GeoFlameOrange,
                                    modifier = Modifier.padding(bottom = 4.dp, start = 4.dp)
                                )
                            }
                            Text(
                                text = "CUET Geophysics Habit",
                                fontSize = 11.sp,
                                color = Color(0xFF94A3B8)
                            )
                        }
                    }

                    // Today's Status Pill
                    Box(
                        modifier = Modifier
                            .background(
                                color = if (streakInfo.isPracticedToday)
                                    GeoSuccessGreen.copy(alpha = 0.15f)
                                else
                                    GeoAmberGold.copy(alpha = 0.15f),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = if (streakInfo.isPracticedToday)
                                    GeoSuccessGreen.copy(alpha = 0.5f)
                                else
                                    GeoAmberGold.copy(alpha = 0.5f),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                    ) {
                        Text(
                            text = if (streakInfo.isPracticedToday) "✓ Active Today" else "⚡ Pending Today",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (streakInfo.isPracticedToday) GeoSuccessGreen else GeoAmberGold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 7-Day Week Progress Dots
                Text(
                    text = "Weekly Consistency",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF94A3B8)
                )
                Spacer(modifier = Modifier.height(8.dp))

                val today = LocalDate.now()
                val startOfWeek = today.with(DayOfWeek.MONDAY)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val days = listOf("M", "T", "W", "T", "F", "S", "S")
                    for (i in 0..6) {
                        val dayDate = startOfWeek.plusDays(i.toLong())
                        val dayEpoch = dayDate.toEpochDay()
                        val isToday = dayDate == today
                        val isCompleted = streakInfo.activeDaysHistory.contains(dayEpoch)

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = days[i],
                                fontSize = 11.sp,
                                fontWeight = if (isToday) FontWeight.Bold else FontWeight.Normal,
                                color = if (isToday) GeoCyanLight else Color(0xFF64748B)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .background(
                                        color = when {
                                            isCompleted -> GeoFlameOrange
                                            isToday -> Color(0xFF1E293B)
                                            else -> Color(0xFF0F172A)
                                        },
                                        shape = CircleShape
                                    )
                                    .border(
                                        width = if (isToday) 1.5.dp else 0.5.dp,
                                        color = when {
                                            isCompleted -> GeoFlameOrange
                                            isToday -> GeoCyanLight
                                            else -> Color(0xFF334155)
                                        },
                                        shape = CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                if (isCompleted) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = "Day active",
                                        tint = Color.White,
                                        modifier = Modifier.size(16.dp)
                                    )
                                } else if (isToday) {
                                    Box(
                                        modifier = Modifier
                                            .size(6.dp)
                                            .background(GeoCyanLight, shape = CircleShape)
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Bottom row with Stats & Action Button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Best Streak: ${streakInfo.maxStreak} days",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFFE2E8F0)
                        )
                        Text(
                            text = "Accuracy: ${streakInfo.accuracyPercentage}% (${streakInfo.totalCorrectAnswers}/${streakInfo.totalQuestionsAnswered})",
                            fontSize = 11.sp,
                            color = Color(0xFF94A3B8)
                        )
                    }

                    Button(
                        onClick = onStartDailyQuiz,
                        modifier = Modifier.testTag("start_daily_quiz_button"),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (streakInfo.isPracticedToday)
                                Color(0xFF0284C7)
                            else
                                GeoFlameOrange
                        ),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (streakInfo.isPracticedToday) "Practice Again" else "Daily Quiz",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
