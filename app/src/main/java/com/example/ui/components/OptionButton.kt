package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.GeoCyanLight
import com.example.ui.theme.GeoErrorRed
import com.example.ui.theme.GeoSuccessGreen

@Composable
fun OptionButton(
    optionLetter: String, // "(a)", "(b)", etc.
    optionText: String,
    isSelected: Boolean,
    isCorrect: Boolean,
    isRevealed: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = when {
        isRevealed && isCorrect -> GeoSuccessGreen.copy(alpha = 0.18f)
        isRevealed && isSelected && !isCorrect -> GeoErrorRed.copy(alpha = 0.18f)
        isSelected -> GeoCyanLight.copy(alpha = 0.15f)
        else -> Color(0xFF131D31)
    }

    val borderColor = when {
        isRevealed && isCorrect -> GeoSuccessGreen
        isRevealed && isSelected && !isCorrect -> GeoErrorRed
        isSelected -> GeoCyanLight
        else -> Color(0xFF263550)
    }

    val letterColor = when {
        isRevealed && isCorrect -> GeoSuccessGreen
        isRevealed && isSelected && !isCorrect -> GeoErrorRed
        isSelected -> GeoCyanLight
        else -> Color(0xFF94A3B8)
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 52.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .border(
                width = if (isSelected || (isRevealed && isCorrect)) 1.5.dp else 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 12.dp)
            .testTag("option_${optionLetter.replace("(", "").replace(")", "")}"),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Option letter circle
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(
                        color = when {
                            isRevealed && isCorrect -> GeoSuccessGreen
                            isRevealed && isSelected && !isCorrect -> GeoErrorRed
                            isSelected -> GeoCyanLight
                            else -> Color(0xFF1E2C44)
                        },
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (isRevealed && isCorrect) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Correct",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                } else if (isRevealed && isSelected && !isCorrect) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Wrong",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                } else {
                    Text(
                        text = optionLetter.replace("(", "").replace(")", "").uppercase(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = if (isSelected) Color(0xFF0F172A) else Color(0xFFE2E8F0)
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = optionText,
                fontSize = 14.sp,
                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                color = if (isSelected || (isRevealed && isCorrect)) Color.White else Color(0xFFCBD5E1),
                modifier = Modifier.weight(1f)
            )
        }
    }
}
