package com.example.contactapp.presentation.navigation

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.contactapp.data.dao.ContactDao
import com.example.contactapp.presentation.screen.AddEditContactScreen

@Composable
fun AppNavigation(dbObject: ContactDao) {
    val NavController = rememberNavController()

    NavHost(navController = NavController, startDestination = ContactScreen) {


        composable<ContactScreen> {
            Text(text = "Main Screen", modifier = Modifier.clickable { NavController.navigate(SaveEditScreen) })
        }

        composable<SaveEditScreen> {
            AddEditContactScreen(dbObject, NavController)
        }

    }
}