package com.krithi.frauddetector.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBar(
    navController: NavController
) {

    val backStackEntry =
        navController.currentBackStackEntryAsState()

    val currentRoute =
        backStackEntry.value?.destination?.route

    NavigationBar {

        NavigationBarItem(
            selected = currentRoute == "home",
            onClick = { navController.navigate("home") },
            icon = {
                Icon(Icons.Default.Home, null)
            },
            label = {
                Text("Home")
            }
        )

        NavigationBarItem(
            selected = currentRoute == "history",
            onClick = { navController.navigate("history") },
            icon = {
                Icon(Icons.Default.History, null)
            },
            label = {
                Text("History")
            }
        )

        NavigationBarItem(
            selected = currentRoute == "settings",
            onClick = { navController.navigate("settings") },
            icon = {
                Icon(Icons.Default.Settings, null)
            },
            label = {
                Text("Settings")
            }
        )
    }
}