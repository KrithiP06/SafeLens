package com.krithi.frauddetector.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.krithi.frauddetector.ui.components.ScreenHeader
import com.krithi.frauddetector.viewmodel.ScanViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PhoneNumberScreen(
    navController: NavController,
    scanViewModel: ScanViewModel
) {

    var phoneNumber by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var isScanning by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        ScreenHeader(
            title = "Check Phone Number",
            onBack = {
                navController.popBackStack()
            }
        )

        Text(
            text = "Check a phone number for suspicious patterns.",
            style = MaterialTheme.typography.bodyMedium
        )

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = {
                phoneNumber = it.filter { character ->
                    character.isDigit() || character == '+'
                }
            },
            label = {
                Text("Enter Phone Number")
            },
            placeholder = {
                Text("+91 9876543210")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            enabled = !isScanning && phoneNumber.isNotBlank(),
            onClick = {

                scope.launch {

                    isScanning = true
                    result = ""

                    delay(1500)

                    val cleanedNumber =
                        phoneNumber
                            .replace("+91", "")
                            .replace(" ", "")
                            .replace("-", "")

                    val isValidIndianNumber =
                        cleanedNumber.length == 10 &&
                                cleanedNumber.firstOrNull() in listOf(
                            '6', '7', '8', '9'
                        )

                    val repeatedPattern =
                        cleanedNumber.all {
                            it == cleanedNumber.firstOrNull()
                        }

                    val knownSpamNumbers = listOf(
                        "9876543210",
                        "9999999999",
                        "8888888888",
                        "7777777777"
                    )

                    result = when {

                        !isValidIndianNumber ->
                            "⚠️ Please enter a valid Indian mobile number"

                        cleanedNumber in knownSpamNumbers ->
                            "🚨 Known Spam Number"

                        repeatedPattern ->
                            "⚠️ Suspicious Number Pattern"

                        else ->
                            "✅ Number format appears safe"
                    }

                    scanViewModel.saveScan(
                        type = "Phone Number",
                        input = phoneNumber,
                        result = result
                    )

                    isScanning = false
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (isScanning) "Scanning..."
                else "📞 Check Number"
            )
        }

        if (isScanning) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {

                Text(
                    text = "🔍 Analyzing phone number...",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        if (result.isNotEmpty()) {

            val isDangerous = result.contains("🚨")
            val isWarning = result.contains("⚠️")

            val resultTitle = when {
                result.contains("valid") ->
                    "Invalid Number"

                isDangerous ->
                    "Spam Number Detected"

                isWarning ->
                    "Suspicious Pattern"

                else ->
                    "Number Format Looks Safe"
            }

            val resultDescription = when {
                result.contains("valid") ->
                    "Please enter a valid 10-digit Indian mobile number."

                isDangerous ->
                    "This number matches a known spam number in the current local database."

                isWarning ->
                    "This number has a pattern that may be suspicious."

                else ->
                    "The number has a valid Indian mobile format and no suspicious pattern was detected."
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = when {
                        isDangerous ->
                            MaterialTheme.colorScheme.errorContainer

                        isWarning ->
                            MaterialTheme.colorScheme.tertiaryContainer

                        else ->
                            MaterialTheme.colorScheme.primaryContainer
                    }
                )
            ) {

                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Text(
                        text = when {
                            isDangerous -> "🚨"
                            isWarning -> "⚠️"
                            else -> "✅"
                        },
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Text(
                        text = resultTitle,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text = resultDescription,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = phoneNumber,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}