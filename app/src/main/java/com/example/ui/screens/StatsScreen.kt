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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Rocket
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.db.QuizAttemptEntity
import com.example.data.repository.StreakInfo
import com.example.ui.theme.GeoAmberGold
import com.example.ui.theme.GeoCyanLight
import com.example.ui.theme.GeoFlameOrange
import com.example.ui.theme.GeoNavyDark
import com.example.ui.theme.GeoSuccessGreen
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatsScreen(
    streakInfo: StreakInfo,
    attempts: List<QuizAttemptEntity>,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = GeoNavyDark,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Streak Analytics & Badges",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = GeoNavyDark)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Big Streak Highlights Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF131D31))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .background(GeoFlameOrange, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocalFireDepartment,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(36.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "${streakInfo.currentStreak} Days",
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.White
                        )

                        Text(
                            text = "Current Daily Streak",
                            fontSize = 13.sp,
                            color = GeoFlameOrange,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(18.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            StatItem(label = "Longest Streak", value = "${streakInfo.maxStreak}d")
                            StatItem(label = "Quizzes Done", value = "${streakInfo.totalQuizzesCompleted}")
                            StatItem(label = "Accuracy", value = "${streakInfo.accuracyPercentage}%")
                            StatItem(label = "Questions", value = "${streakInfo.totalQuestionsAnswered}")
                        }
                    }
                }
            }

            // Milestone Badges
            item {
                Text(
                    text = "Geophysics Mastery Badges",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    BadgeRow(
                        title = "Orbital Pioneer",
                        desc = "Complete your first CUET quiz session",
                        icon = Icons.Default.Rocket,
                        unlocked = streakInfo.totalQuizzesCompleted >= 1
                    )
                    BadgeRow(
                        title = "3-Day Fire",
                        desc = "Maintain a 3-day continuous daily streak",
                        icon = Icons.Default.LocalFireDepartment,
                        unlocked = streakInfo.maxStreak >= 3
                    )
                    BadgeRow(
                        title = "Galilean Scholar",
                        desc = "Answer 10+ questions with >70% accuracy",
                        icon = Icons.Default.Psychology,
                        unlocked = streakInfo.totalQuestionsAnswered >= 10 && streakInfo.accuracyPercentage >= 70
                    )
                    BadgeRow(
                        title = "7-Day Orbit Master",
                        desc = "Achieve a full 1-week continuous daily streak",
                        icon = Icons.Default.WorkspacePremium,
                        unlocked = streakInfo.maxStreak >= 7
                    )
                }
            }

            // Recent Attempt History
            item {
                Text(
                    text = "Recent Quiz History",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            if (attempts.isEmpty()) {
                item {
                    Text(
                        text = "No quiz attempts yet. Complete your daily challenge to start tracking!",
                        fontSize = 13.sp,
                        color = Color(0xFF94A3B8)
                    )
                }
            } else {
                items(attempts.take(10)) { attempt ->
                    val dateFormat = SimpleDateFormat("MMM dd, hh:mm a", Locale.getDefault())
                    val dateStr = dateFormat.format(Date(attempt.timestamp))
                    val pct = if (attempt.totalQuestions > 0)
                        ((attempt.score.toDouble() / attempt.totalQuestions) * 100).toInt()
                    else 0

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF131F37))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = attempt.quizTitle,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Text(
                                    text = "$dateStr • ${attempt.timeTakenSeconds}s",
                                    fontSize = 11.sp,
                                    color = Color(0xFF94A3B8)
                                )
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "${attempt.score}/${attempt.totalQuestions}",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (pct >= 70) GeoSuccessGreen else GeoFlameOrange
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "($pct%)",
                                    fontSize = 12.sp,
                                    color = Color(0xFFCBD5E1)
                                )
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun StatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = label,
            fontSize = 11.sp,
            color = Color(0xFF94A3B8)
        )
    }
}

@Composable
fun BadgeRow(
    title: String,
    desc: String,
    icon: ImageVector,
    unlocked: Boolean
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (unlocked) Color(0xFF162544) else Color(0xFF0F172A)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = if (unlocked) GeoAmberGold.copy(alpha = 0.4f) else Color(0xFF1E293B),
                    shape = RoundedCornerShape(14.dp)
                )
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = if (unlocked) GeoAmberGold.copy(alpha = 0.2f) else Color(0xFF1E293B),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = if (unlocked) GeoAmberGold else Color(0xFF64748B),
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (unlocked) Color.White else Color(0xFF94A3B8)
                )
                Text(
                    text = desc,
                    fontSize = 11.sp,
                    color = if (unlocked) Color(0xFFCBD5E1) else Color(0xFF64748B)
                )
            }

            if (unlocked) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Unlocked",
                    tint = GeoSuccessGreen,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
