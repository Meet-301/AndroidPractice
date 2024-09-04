package com.example.contactapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.contactapp.data.dao.ContactDao
import com.example.contactapp.presentation.screen.AddEditContactScreen
import com.example.contactapp.presentation.screen.MainScreen

@Composable
fun AppNavigation(dbObject: ContactDao) {
    val NavController = rememberNavController()

    NavHost(navController = NavController, startDestination = ContactScreen) {

        composable<ContactScreen> {
            MainScreen(dbObject, NavController)
        }

        composable<SaveEditScreen> {
            AddEditContactScreen(dbObject, NavController)
        }

    }
}