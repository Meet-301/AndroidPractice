package com.example.contactapp.ui_layer.screen

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.contactapp.R
import com.example.contactapp.ui_layer.state.ContactState
import com.example.contactapp.ui_layer.viewmodel.ContactAppViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AddEditScreenUI(
    navController: NavController,
    viewModel: ContactAppViewModel = hiltViewModel(),
    state: ContactState,
    onEvent: () -> Unit
) {

    val context = LocalContext.current

    val image by remember {
        mutableStateOf(R.drawable.ic_launcher_foreground)
    }
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
            if (it != null) {
                val inputStream = context.contentResolver.openInputStream(it)
                val byteArray = inputStream?.readBytes()
                state.image.value = byteArray
                inputStream?.close()
            }
        }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (state.image.value == null) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.size(70.dp).clickable {
                    launcher.launch("image/*")
                },
                contentScale = ContentScale.Fit
            )
        } else {
            Image(
                bitmap = BitmapFactory.decodeByteArray(
                    state.image.value,
                    0,
                    state.image.value!!.size
                ).asImageBitmap(), contentDescription = null,
                modifier = Modifier.size(width = 150.dp, height = 150.dp).clip(shape = CircleShape).clickable {
                    launcher.launch("image/*")
                }
                ,
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(value = state.name.value,
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
                state.name.value = newName
            },
            placeholder = {
                Text(text = "Name")
            })

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(value = state.number.value,
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
                state.number.value = newNumber
            },
            placeholder = {
                Text(text = "Phone Number")
            })


        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(value = state.email.value,
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
                state.email.value = newEmail
            },
            placeholder = {
                Text(text = "Email")
            })


        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                onEvent.invoke()
                Toast.makeText(context, "Contact Saved", Toast.LENGTH_SHORT).show()
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