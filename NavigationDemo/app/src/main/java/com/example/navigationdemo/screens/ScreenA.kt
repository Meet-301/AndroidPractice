package com.example.navigationdemo.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.navigationdemo.navigation.ScreenB

@Composable
fun ScreenAUI(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Screen A", modifier = Modifier.clickable {
            navController.navigate(ScreenB)
        })
    }
}