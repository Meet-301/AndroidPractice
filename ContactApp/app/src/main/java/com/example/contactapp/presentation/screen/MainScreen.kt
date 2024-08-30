package com.example.contactapp.presentation.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.contactapp.data.dao.ContactDao
import com.example.contactapp.presentation.navigation.SaveEditScreen

@Composable
fun MainScreen(dbObject: ContactDao) {

    var navController = rememberNavController()

    Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
       FloatingActionButton(onClick = { navController.navigate(SaveEditScreen) }) {
           Icon(imageVector = Icons.Filled.Add, contentDescription = null)
       }
    }) { innerPadding ->
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            items(dbObject.getAllContacts()) {
                Card(modifier = Modifier.fillMaxSize()) {
                    Text(text = it.name)
                    Text(text = it.number)
                    Text(text = it.email)
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }


}