package com.example.contactapp.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.contactapp.R
import com.example.contactapp.data.dao.ContactDao
import com.example.contactapp.presentation.navigation.SaveEditScreen

@Composable
fun MainScreen(dbObject: ContactDao, NavController: NavHostController) {

    Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
       FloatingActionButton(onClick = { NavController.navigate(SaveEditScreen) }) {
           Icon(imageVector = Icons.Filled.Add, contentDescription = null)
       }
    }) { innerPadding ->
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            items(dbObject.getAllContacts()) {
                Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.Transparent)) {
                   Row {
                       Icon(imageVector = Icons.Filled.Person, contentDescription = null, modifier = Modifier
                           .padding(10.dp)
                           .size(55.dp))
                       Column(modifier = Modifier.weight(1f)) {
                           Spacer(modifier = Modifier.height(5.dp))
                           Text(text = it.name, fontFamily = FontFamily(Font(R.font.acme)), fontSize = 27.sp)
                           Text(text = it.number, modifier = Modifier
                               .size(54.dp)
                               .fillMaxWidth(), maxLines = 1)
                       }
                       IconButton(onClick = { dbObject.deleteContact(it) }) {
                           Icon(imageVector = Icons.Filled.Delete, contentDescription = null, modifier = Modifier.size(50.dp), tint = Color.Red)
                       }
                   }
                }
            }
        }
    }


}