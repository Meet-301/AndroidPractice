package com.example.navigationdemo.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.navigationdemo.navigation.ScreenA

@Composable
fun ScreenBUI(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Screen B", modifier = Modifier.clickable {
            navController.navigate(ScreenA)
        })
    }
}