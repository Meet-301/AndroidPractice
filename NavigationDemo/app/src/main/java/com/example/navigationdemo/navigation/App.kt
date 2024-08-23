package com.example.navigationdemo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationdemo.screens.ScreenAUI
import com.example.navigationdemo.screens.ScreenBUI

@Composable
fun App(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenA) {
        composable<ScreenA> {
            ScreenAUI(navController)
        }

        composable<ScreenB> {
            ScreenBUI(navController)
        }
    }
}