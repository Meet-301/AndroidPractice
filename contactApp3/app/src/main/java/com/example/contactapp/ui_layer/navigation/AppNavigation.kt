package com.example.contactapp.ui_layer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.contactapp.ui_layer.screen.AddEditScreenUI
import com.example.contactapp.ui_layer.screen.HomeScreenUI

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = HomeScreen) {

        composable<HomeScreen> {
            HomeScreenUI(navController)
        }

        composable<AddEditScreen> {
            AddEditScreenUI(navController)
        }

    }

}