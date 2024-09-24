package com.example.contactapp.ui_layer.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.contactapp.ui_layer.navigation.AddEditScreen
import com.example.contactapp.ui_layer.viewmodel.ContactAppViewModel

@Composable
fun HomeScreenUI(navController: NavController, viewModel: ContactAppViewModel = hiltViewModel()) {

    val state = viewModel.state.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(AddEditScreen)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            items(state.value.contactList) {
                Card(onClick = {}, modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 12.dp)) {
                    Column (modifier = Modifier.fillMaxSize().padding(10.dp)) {
                        Text(text = it.name)
                        Text(text = it.number)
                        Text(text = it.email)
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }

}