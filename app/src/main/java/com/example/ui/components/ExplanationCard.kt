package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Functions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.GeoAmberGold
import com.example.ui.theme.GeoCyanLight
import com.example.ui.theme.GeoSuccessGreen

@Composable
fun ExplanationCard(
    correctOptionLetter: String,
    explanationText: String,
    formulaTakeaway: String = "",
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFF0F2238),
                shape = RoundedCornerShape(18.dp)
            )
            .border(
                width = 1.dp,
                color = GeoCyanLight.copy(alpha = 0.4f),
                shape = RoundedCornerShape(18.dp)
            )
            .padding(16.dp)
            .testTag("explanation_card")
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Header
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = null,
                    tint = GeoAmberGold,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Explanation & Physics Concept",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = GeoCyanLight
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .background(GeoSuccessGreen.copy(alpha = 0.2f), shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "Correct: $correctOptionLetter",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = GeoSuccessGreen
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Explanation body
            Text(
                text = explanationText,
                fontSize = 13.sp,
                lineHeight = 20.sp,
                color = Color(0xFFE2E8F0)
            )

            // Formula box if present
            if (formulaTakeaway.isNotBlank()) {
                Spacer(modifier = Modifier.height(12.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF091424), shape = RoundedCornerShape(10.dp))
                        .border(0.5.dp, GeoCyanLight.copy(alpha = 0.25f), shape = RoundedCornerShape(10.dp))
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Functions,
                            contentDescription = null,
                            tint = GeoAmberGold,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = formulaTakeaway,
                            fontSize = 12.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Medium,
                            color = GeoCyanLight
                        )
                    }
                }
            }
        }
    }
}
