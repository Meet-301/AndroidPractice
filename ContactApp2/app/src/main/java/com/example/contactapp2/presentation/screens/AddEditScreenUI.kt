package com.example.contactapp2.presentation.screens

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.contactapp2.ContactAppViewModel
import com.example.contactapp2.R
import com.example.contactapp2.database.tables.Contact

@Composable
fun AddEditScreenUI(
    navController: NavHostController,
    viewModel: ContactAppViewModel,
    id: Int?
) {
    var txtName by rememberSaveable { mutableStateOf("") }
    var txtNumber by rememberSaveable { mutableStateOf("") }
    var txtEmail by rememberSaveable { mutableStateOf("") }
    var context = LocalContext.current
    var customCoroutine = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally)  {
        Spacer(modifier = Modifier.height(30.dp))

        Text(text = "Add a Contact", fontFamily = FontFamily(Font(R.font.acme)), fontSize = 40.sp, textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(50.dp))

        Row {
            Spacer(modifier = Modifier.size(10.dp))
            TextField(value = txtName, leadingIcon = { Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = null) } , placeholder = { Text(text = "Name") } , onValueChange = {
                txtName = it
            }, colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent, focusedContainerColor = Color.Transparent))
        }

        Spacer(modifier = Modifier.height(50.dp))

        Row {
            Spacer(modifier = Modifier.size(10.dp))
            TextField(value = txtNumber, leadingIcon = { Icon(imageVector = Icons.Filled.Call, contentDescription = null) }, placeholder = { Text(text = "Number") } , keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) , onValueChange = {
                txtNumber = it
            }, colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent, focusedContainerColor = Color.Transparent))
        }

        Spacer(modifier = Modifier.height(50.dp))

        Row {
            Spacer(modifier = Modifier.size(10.dp))
            TextField(value = txtEmail, leadingIcon = { Icon(imageVector = Icons.Filled.Email, contentDescription = null) } ,placeholder = { Text(text = "Email") } , keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email) , onValueChange = {
                txtEmail = it
            }, colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent, focusedContainerColor = Color.Transparent))
        }

        Spacer(modifier = Modifier.height(50.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(onClick = {

                if (txtName.isEmpty() || txtNumber.isEmpty() || txtEmail.isEmpty()) {
                    Toast.makeText(context, "Please Fill All Fields", Toast.LENGTH_SHORT).show()
                }
                else if (txtNumber.length != 10) {
                    Toast.makeText(context, "Invalid Number", Toast.LENGTH_SHORT).show()
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(txtEmail).matches()) {
                    Toast.makeText(context, "Invalid Email", Toast.LENGTH_SHORT).show()
                }
                else {
                    val contact = Contact(id =  id, name = txtName, email = txtEmail, phone = txtNumber)
                    viewModel.addUpdateContact(contact)
                    navController.navigateUp()
                }

            }, colors = ButtonDefaults.buttonColors(Color.Black), modifier = Modifier.fillMaxWidth(.7f)) {
                Text(text = "Add")
            }
        }

    }
}