package com.example.contactapp.ui_layer.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.contactapp.ui_layer.viewmodel.ContactAppViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AddEditScreenUI(
    navController: NavController,
    viewModel: ContactAppViewModel = hiltViewModel()
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(value = viewModel.state.value.name.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            label = { Text(text = "Name") },
            singleLine = true,
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            onValueChange = { newName ->
                viewModel.state.value.name.value = newName
            },
            placeholder = {
                Text(text = "Name")
            })

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(value = viewModel.state.value.number.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            shape = RoundedCornerShape(15.dp),
            label = { Text(text = "Phone Number") },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            onValueChange = { newNumber ->
                viewModel.state.value.number.value = newNumber
            },
            placeholder = {
                Text(text = "Phone Number")
            })


        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(value = viewModel.state.value.email.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            label = { Text(text = "Email") },
            singleLine = true,
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            onValueChange = { newEmail ->
                viewModel.state.value.email.value = newEmail
            },
            placeholder = {
                Text(text = "Email")
            })


        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                viewModel.upsertContact()
                navController.navigateUp()
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            elevation = ButtonDefaults.buttonElevation(pressedElevation = 6.dp)
        ) {
            Text(text = "Save")
        }
    }

}