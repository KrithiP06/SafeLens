package com.krithi.frauddetector.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun FeatureCard(
    icon: ImageVector,
    title: String,
    description: String,
    onClick: () -> Unit
) {
    val isDarkMode =
        MaterialTheme.colorScheme.background == Color(0xFF080B14)

    val cardColor = if (isDarkMode) {
        Color(0xFF151A2A)
    } else {
        MaterialTheme.colorScheme.surface
    }

    val primaryText = if (isDarkMode) {
        Color.White
    } else {
        MaterialTheme.colorScheme.onSurface
    }

    val secondaryText = if (isDarkMode) {
        Color(0xFF9EA6BD)
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = cardColor,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onClick() }
            .padding(18.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(52.dp)
                    .background(
                        color = Color(0xFF8B7CFF).copy(alpha = 0.14f),
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = Color(0xFF8B7CFF),
                    modifier = Modifier.size(26.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = primaryText
                )

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = secondaryText
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Open $title",
                tint = Color(0xFF8B7CFF),
                modifier = Modifier.size(22.dp)
            )
        }
    }
}