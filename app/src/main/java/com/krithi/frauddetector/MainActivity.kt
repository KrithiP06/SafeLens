package com.krithi.frauddetector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.krithi.frauddetector.ui.navigation.AppNavigation
import com.krithi.frauddetector.ui.theme.FraudDetectorTheme
import com.krithi.frauddetector.viewmodel.ScanViewModel
import com.krithi.frauddetector.viewmodel.ScanViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            var darkMode by rememberSaveable {
                mutableStateOf(true)
            }

            FraudDetectorTheme(
                darkTheme = darkMode
            ) {

                val app = application as SafeLensApplication

                val scanViewModel: ScanViewModel = viewModel(
                    factory = ScanViewModelFactory(
                        app.container.repository,
                        app.container.urlScannerRepository
                    )
                )

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { _ ->

                    AppNavigation(
                        scanViewModel = scanViewModel,
                        darkMode = darkMode,
                        onDarkModeChange = {
                            darkMode = it
                        }
                    )
                }
            }
        }
    }
}