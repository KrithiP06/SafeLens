package com.krithi.frauddetector.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.krithi.frauddetector.ui.components.ScreenHeader
import com.krithi.frauddetector.viewmodel.ScanViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SmsAnalyzerScreen(
    navController: NavController,
    scanViewModel: ScanViewModel
) {
    var message by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var isScanning by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    // Detect current theme
    val isDarkMode =
        MaterialTheme.colorScheme.background == Color(0xFF080B14)

    // Theme-aware colors
    val backgroundColor = if (isDarkMode) {
        Color(0xFF080B14)
    } else {
        MaterialTheme.colorScheme.background
    }

    val glassColor = if (isDarkMode) {
        Color(0xFF151A2A).copy(alpha = 0.88f)
    } else {
        MaterialTheme.colorScheme.surface
    }

    val glassBorder = if (isDarkMode) {
        Color(0xFF343B55).copy(alpha = 0.75f)
    } else {
        MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)
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

    val mutedText = if (isDarkMode) {
        Color(0xFF777F98)
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

    val glassInnerColor = if (isDarkMode) {
        Color(0xFF0D111D).copy(alpha = 0.78f)
    } else {
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f)
    }

    val glassInnerBorder = if (isDarkMode) {
        Color(0xFF30364D)
    } else {
        MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
    }

    // SafeLens accent colors
    val purpleAccent = Color(0xFF8B7CFF)
    val greenAccent = Color(0xFF52D9A0)
    val yellowAccent = Color(0xFFFFC857)
    val redAccent = Color(0xFFFF5C68)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(rememberScrollState())
            .padding(
                start = 20.dp,
                top = 20.dp,
                end = 20.dp,
                bottom = 32.dp
            ),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {

        // Header
        ScreenHeader(
            title = "Analyze SMS",
            onBack = {
                navController.popBackStack()
            }
        )

        Text(
            text = "Analyze a message for suspicious or scam-like content.",
            style = MaterialTheme.typography.bodyLarge,
            color = secondaryText
        )

        // Security status card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = glassColor
            ),
            border = BorderStroke(
                1.dp,
                glassBorder
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Default.Security,
                    contentDescription = null,
                    tint = purpleAccent
                )

                Spacer(
                    modifier = Modifier.padding(horizontal = 6.dp)
                )

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "SafeLens Message Analysis",
                        style = MaterialTheme.typography.titleMedium,
                        color = primaryText
                    )

                    Spacer(
                        modifier = Modifier.height(2.dp)
                    )

                    Text(
                        text = "Local scam-pattern detection enabled",
                        style = MaterialTheme.typography.bodySmall,
                        color = mutedText
                    )
                }

                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = greenAccent
                )
            }
        }

        // Message input section
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = glassColor
            ),
            border = BorderStroke(
                1.dp,
                glassBorder
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Text(
                    text = "MESSAGE CONTENT",
                    style = MaterialTheme.typography.labelSmall,
                    color = mutedText
                )

                OutlinedTextField(
                    value = message,
                    onValueChange = {
                        message = it
                    },
                    label = {
                        Text("Enter SMS Message")
                    },
                    placeholder = {
                        Text("Paste the message you received...")
                    },
                    minLines = 5,
                    maxLines = 8,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Message,
                            contentDescription = null,
                            tint = purpleAccent
                        )
                    }
                )

                Text(
                    text = "Never share OTPs, passwords, or other sensitive information.",
                    style = MaterialTheme.typography.bodySmall,
                    color = mutedText
                )
            }
        }

        // Analyze button
        Button(
            enabled = !isScanning && message.isNotBlank(),
            onClick = {
                scope.launch {

                    isScanning = true
                    result = ""

                    delay(1500)

                    val lowerMessage = message.lowercase()

                    val suspiciousKeywords = listOf(
                        "urgent",
                        "account suspended",
                        "account blocked",
                        "verify your account",
                        "click here",
                        "click the link",
                        "otp",
                        "password",
                        "bank",
                        "credit card",
                        "debit card",
                        "claim your prize",
                        "you have won",
                        "winner",
                        "lottery",
                        "free money",
                        "refund",
                        "kyc",
                        "limited time",
                        "act now",
                        "immediately",
                        "confirm your identity"
                    )

                    val matchedKeywords =
                        suspiciousKeywords.filter {
                            lowerMessage.contains(it)
                        }

                    result = when {
                        matchedKeywords.size >= 3 ->
                            "🚨 Highly Suspicious Message"

                        matchedKeywords.size >= 1 ->
                            "⚠️ Potentially Suspicious Message"

                        else ->
                            "✅ Message Appears Safe"
                    }

                    scanViewModel.saveScan(
                        type = "SMS",
                        input = message,
                        result = result
                    )

                    isScanning = false
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF7567F8),
                contentColor = Color.White
            )
        ) {

            if (isScanning) {

                CircularProgressIndicator(
                    modifier = Modifier.height(22.dp),
                    color = Color.White,
                    strokeWidth = 2.dp
                )

                Spacer(
                    modifier = Modifier.padding(horizontal = 6.dp)
                )

                Text("Analyzing...")

            } else {

                Text(
                    text = "💬  Analyze Message",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        // Scanning state
        if (isScanning) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isDarkMode) {
                        Color(0xFF151A2A).copy(alpha = 0.78f)
                    } else {
                        MaterialTheme.colorScheme.surfaceVariant
                    }
                ),
                border = BorderStroke(
                    1.dp,
                    purpleAccent.copy(alpha = 0.45f)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    CircularProgressIndicator(
                        modifier = Modifier.height(22.dp),
                        color = purpleAccent,
                        strokeWidth = 2.dp
                    )

                    Spacer(
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    Column {

                        Text(
                            text = "Analyzing Message",
                            style = MaterialTheme.typography.titleMedium,
                            color = primaryText
                        )

                        Text(
                            text = "Checking suspicious scam patterns...",
                            style = MaterialTheme.typography.bodySmall,
                            color = secondaryText
                        )
                    }
                }
            }
        }

        // Result
        if (result.isNotEmpty()) {

            val isDangerous = result.contains("🚨")
            val isWarning = result.contains("⚠️")

            val resultTitle = when {
                isDangerous ->
                    "Highly Suspicious"

                isWarning ->
                    "Potential Scam"

                else ->
                    "Message Looks Safe"
            }

            val resultDescription = when {
                isDangerous ->
                    "This message contains multiple patterns commonly associated with scam or phishing messages."

                isWarning ->
                    "This message contains language or patterns that may indicate a suspicious message."

                else ->
                    "No common scam patterns were detected in this message."
            }

            val accentColor = when {
                isDangerous ->
                    redAccent

                isWarning ->
                    yellowAccent

                else ->
                    greenAccent
            }

            val resultIcon = when {
                isDangerous ->
                    Icons.Default.Warning

                isWarning ->
                    Icons.Default.Warning

                else ->
                    Icons.Default.CheckCircle
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(
                    containerColor = glassColor
                ),
                border = BorderStroke(
                    1.dp,
                    accentColor.copy(alpha = 0.55f)
                )
            ) {

                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    // Result heading
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = resultIcon,
                            contentDescription = null,
                            tint = accentColor
                        )

                        Spacer(
                            modifier = Modifier.padding(horizontal = 6.dp)
                        )

                        Text(
                            text = resultTitle,
                            style = MaterialTheme.typography.titleLarge,
                            color = primaryText
                        )
                    }

                    // Description
                    Text(
                        text = resultDescription,
                        style = MaterialTheme.typography.bodyMedium,
                        color = secondaryText
                    )

                    // Analyzed message card
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = glassInnerColor
                        ),
                        border = BorderStroke(
                            1.dp,
                            glassInnerBorder
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(14.dp)
                        ) {

                            Text(
                                text = "ANALYZED MESSAGE",
                                style = MaterialTheme.typography.labelSmall,
                                color = mutedText
                            )

                            Spacer(
                                modifier = Modifier.height(5.dp)
                            )

                            Text(
                                text = message,
                                style = MaterialTheme.typography.bodyMedium,
                                color = primaryText
                            )
                        }
                    }

                    // Recommendation
                    Text(
                        text = when {

                            isDangerous ->
                                "⚠ Do not click links or share personal information."

                            isWarning ->
                                "⚠ Verify the sender before taking any action."

                            else ->
                                "✓ No common scam patterns were detected."
                        },
                        style = MaterialTheme.typography.bodySmall,
                        color = accentColor
                    )
                }
            }
        }
    }
}