package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.GeophysicsQuestionBank
import com.example.ui.components.ExplanationCard
import com.example.ui.theme.GeoAmberGold
import com.example.ui.theme.GeoCyan
import com.example.ui.theme.GeoCyanLight
import com.example.ui.theme.GeoNavyDark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyNotesScreen(
    bookmarkedIds: List<Int>,
    onToggleBookmark: (Int) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedChapter by remember { mutableStateOf(0) } // 0 = All
    var showOnlyBookmarked by remember { mutableStateOf(false) }

    val filteredQuestions by remember(searchQuery, selectedChapter, showOnlyBookmarked, bookmarkedIds) {
        derivedStateOf {
            GeophysicsQuestionBank.questions.filter { q ->
                val matchesChapter = selectedChapter == 0 || q.chapter == selectedChapter
                val matchesQuery = searchQuery.isBlank() ||
                        q.questionText.contains(searchQuery, ignoreCase = true) ||
                        q.topic.contains(searchQuery, ignoreCase = true) ||
                        q.explanation.contains(searchQuery, ignoreCase = true) ||
                        q.formulaTakeaway.contains(searchQuery, ignoreCase = true)
                val matchesBookmark = !showOnlyBookmarked || bookmarkedIds.contains(q.id)

                matchesChapter && matchesQuery && matchesBookmark
            }
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = GeoNavyDark,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Study Questions & Notes",
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            // Search Input
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("study_search_input"),
                placeholder = { Text("Search concept, formula, or law...", color = Color(0xFF64748B)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = GeoCyanLight
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear", tint = Color.Gray)
                        }
                    }
                },
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF131F37),
                    unfocusedContainerColor = Color(0xFF131F37),
                    focusedBorderColor = GeoCyanLight,
                    unfocusedBorderColor = Color(0xFF1E293B),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Filter Chips (All, Ch 1, Ch 2, Ch 3, Bookmarks)
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    FilterChip(
                        selected = selectedChapter == 0 && !showOnlyBookmarked,
                        onClick = {
                            selectedChapter = 0
                            showOnlyBookmarked = false
                        },
                        label = { Text("All (${GeophysicsQuestionBank.questions.size})") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = GeoCyan,
                            selectedLabelColor = Color.White
                        )
                    )
                }
                item {
                    FilterChip(
                        selected = selectedChapter == 1 && !showOnlyBookmarked,
                        onClick = {
                            selectedChapter = 1
                            showOnlyBookmarked = false
                        },
                        label = { Text("Ch 1: Mechanics") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = GeoCyan,
                            selectedLabelColor = Color.White
                        )
                    )
                }
                item {
                    FilterChip(
                        selected = selectedChapter == 2 && !showOnlyBookmarked,
                        onClick = {
                            selectedChapter = 2
                            showOnlyBookmarked = false
                        },
                        label = { Text("Ch 2: Conservation") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = GeoCyan,
                            selectedLabelColor = Color.White
                        )
                    )
                }
                item {
                    FilterChip(
                        selected = selectedChapter == 3 && !showOnlyBookmarked,
                        onClick = {
                            selectedChapter = 3
                            showOnlyBookmarked = false
                        },
                        label = { Text("Ch 3: Rotation") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = GeoCyan,
                            selectedLabelColor = Color.White
                        )
                    )
                }
                item {
                    FilterChip(
                        selected = showOnlyBookmarked,
                        onClick = { showOnlyBookmarked = !showOnlyBookmarked },
                        label = { Text("Saved (${bookmarkedIds.size})") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Bookmark,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = if (showOnlyBookmarked) Color.White else GeoAmberGold
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = GeoAmberGold,
                            selectedLabelColor = Color.White
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Questions list
            if (filteredQuestions.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 50.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("No questions found matching your search", color = Color(0xFF94A3B8))
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Try searching for 'Coriolis', 'Moment', or 'Rocket'", fontSize = 12.sp, color = GeoCyanLight)
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    items(filteredQuestions) { question ->
                        val isBookmarked = bookmarkedIds.contains(question.id)

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(18.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFF131D31))
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .background(GeoCyan.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                                            .padding(horizontal = 8.dp, vertical = 3.dp)
                                    ) {
                                        Text(
                                            text = "Ch ${question.chapter}: ${question.topic}",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = GeoCyanLight
                                        )
                                    }

                                    IconButton(
                                        onClick = { onToggleBookmark(question.id) },
                                        modifier = Modifier.size(32.dp)
                                    ) {
                                        Icon(
                                            imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                                            contentDescription = "Save",
                                            tint = if (isBookmarked) GeoAmberGold else Color(0xFF64748B),
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = question.questionText,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.White
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                // Options summary
                                val letters = listOf("(a)", "(b)", "(c)", "(d)")
                                question.options.forEachIndexed { idx, opt ->
                                    val isCorrect = idx == question.correctIndex
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 2.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "${letters[idx]} $opt",
                                            fontSize = 13.sp,
                                            color = if (isCorrect) GeoCyanLight else Color(0xFF94A3B8),
                                            fontWeight = if (isCorrect) FontWeight.Bold else FontWeight.Normal
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(12.dp))

                                // Proper explanation card
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
    }
}
