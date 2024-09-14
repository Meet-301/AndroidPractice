package com.example.contactapp2.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.contactapp2.ContactAppViewModel
import com.example.contactapp2.database.tables.Contact
import com.example.contactapp2.presentation.navigation.AddEditScreen
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenUI(navController: NavHostController,
                 viewModel: ContactAppViewModel) {

    var contacts by rememberSaveable {
      mutableStateOf<List<Contact>>(emptyList<Contact>())
    }
    
    LaunchedEffect(key1 = viewModel.db.contactDao().getContacts()) {
        viewModel.db.contactDao().getContacts().collectLatest {
            contacts = it
        }
    }


    Scaffold(topBar = { TopAppBar(title = { Text(text = "Contacts") }) }, floatingActionButton = {
        FloatingActionButton(onClick = {
            navController.navigate(AddEditScreen(null))
        }) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = null)
        }
    }) {
        Column(Modifier.padding(it)) {
            LazyColumn {
                items(contacts) {
                    Card(
                        Modifier
                            .padding(8.dp)
                            .clickable {
                                navController.navigate(AddEditScreen(it.id))
                            }) {
                        Box(Modifier.fillMaxSize()) {
                            Text(text = it.name)
                        }
                    }
                }
            }
        }
    }
}