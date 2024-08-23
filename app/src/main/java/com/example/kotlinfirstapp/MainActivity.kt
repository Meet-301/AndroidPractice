package com.example.kotlinfirstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlinfirstapp.ui.theme.KotlinFirstAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinFirstAppTheme {
               Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                   var mText : String = ""

                   Column(modifier = Modifier
                       .fillMaxSize()
                       .background(color = Color(0xfffed523))
                       .padding(innerPadding)
                       .padding(top = 150.dp),
                       verticalArrangement = Arrangement.Top,
                       horizontalAlignment = Alignment.CenterHorizontally) {

                       Text(text = "Create an Account",
                           fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
                       
                       Spacer(modifier = Modifier.height(100.dp))

                       TextField(value = mText, onValueChange = {
                           mText = it},
                           placeholder = {
                               Text(text = "Email")
                           },colors = TextFieldDefaults.colors(
                               focusedContainerColor = Color.Transparent,
                               unfocusedContainerColor = Color.Transparent
                           ), leadingIcon = {
                               Icon(imageVector = Icons.Rounded.Email, contentDescription = null)
                           })
                       Spacer(modifier = Modifier.height(15.dp))
                       TextField(value = mText, onValueChange = {
                           mText = it},
                           placeholder = {
                               Text(text = "Username")
                           },colors = TextFieldDefaults.colors(
                               focusedContainerColor = Color.Transparent,
                               unfocusedContainerColor = Color.Transparent
                           ), leadingIcon = {
                               Icon(imageVector = Icons.Rounded.Person, contentDescription = null)
                           }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                       )
                       Spacer(modifier = Modifier.height(15.dp))
                       TextField(value = mText, onValueChange = {
                           mText = it},
                           placeholder = {
                               Text(text = "Password")
                           },colors = TextFieldDefaults.colors(
                               focusedContainerColor = Color.Transparent,
                               unfocusedContainerColor = Color.Transparent
                           ), leadingIcon = {
                               Icon(imageVector = Icons.Rounded.Lock, contentDescription = null)
                           }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                       )

                       Spacer(modifier = Modifier.height(30.dp))

                       Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                           Checkbox(checked = true, onCheckedChange = {})
                           Text(text = "I read and agree")
                           Text(text = " privacy policy", color = Color.Red)
                       }

                       Button(onClick = { /*TODO*/ },
                           modifier = Modifier.fillMaxWidth(.63f),
                           colors = ButtonDefaults.buttonColors(
                           containerColor = Color(0xff000000),
                           contentColor = Color(0xffffffff),
                       )) {
                           Text(text = "Register")
                       }
                   }

               }
            }
        }
    }
}