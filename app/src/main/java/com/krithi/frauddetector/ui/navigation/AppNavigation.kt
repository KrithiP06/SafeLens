package com.krithi.frauddetector.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.krithi.frauddetector.ui.components.BottomNavBar
import com.krithi.frauddetector.ui.screens.*
import com.krithi.frauddetector.viewmodel.ScanViewModel

@Composable
fun AppNavigation(
    scanViewModel: ScanViewModel,
    darkMode: Boolean,
    onDarkModeChange: (Boolean) -> Unit
) {
    val navController = rememberNavController()

    val currentBackStackEntry =
        navController.currentBackStackEntryAsState()

    val currentDestination =
        currentBackStackEntry.value?.destination

    val isHome =
        currentDestination?.hierarchy?.any {
            it.route == "home"
        } == true

    BackHandler(enabled = !isHome) {
        navController.navigate("home") {
            popUpTo("home") {
                inclusive = false
            }
            launchSingleTop = true
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavBar(navController)
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            composable("home") {
                HomeScreen(
                    navController = navController,
                    darkMode = darkMode
                )
            }

            composable("phone") {
                PhoneNumberScreen(
                    navController = navController,
                    scanViewModel = scanViewModel
                )
            }

            composable("url") {
                UrlScannerScreen(
                    navController = navController,
                    scanViewModel = scanViewModel
                )
            }

            composable("sms") {
                SmsAnalyzerScreen(
                    navController = navController,
                    scanViewModel = scanViewModel
                )
            }

            composable("history") {
                HistoryScreen(
                    scanViewModel = scanViewModel
                )
            }

            composable("settings") {
                SettingsScreen(
                    darkMode = darkMode,
                    onDarkModeChange = onDarkModeChange
                )
            }
        }
    }
}