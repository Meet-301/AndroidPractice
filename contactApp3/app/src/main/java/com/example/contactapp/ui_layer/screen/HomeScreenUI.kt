package com.example.contactapp.ui_layer.screen

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.contactapp.ui_layer.navigation.AddEditScreen
import com.example.contactapp.ui_layer.state.ContactState
import com.example.contactapp.ui_layer.viewmodel.ContactAppViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenUI(
    navController: NavController,
    viewModel: ContactAppViewModel = hiltViewModel(),
    state: ContactState
) {

    val context = LocalContext.current

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
            items(state.contactList) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 12.dp)
                        .combinedClickable(
                            onClick = {

                            },
                            onDoubleClick = {
                                navController.navigate(AddEditScreen)
                                state.id.value = it.id
                                state.name.value = it.name
                                state.number.value = it.number
                                state.email.value = it.email
                            },
                            onLongClick = {
                                val callIntent = Intent(Intent.ACTION_CALL)
                                callIntent.setData(Uri.parse("tel:${it.number}"))
                                context.startActivity(callIntent)
                            }
                        )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        if (it.image == null) {
                            val test = it.name.split(" ", ignoreCase = false)
                            val test2 = test.filter { it.isNotBlank() }
                            val firstLetter = test2.map { it.first() }
                            Text(text = firstLetter.joinToString(""))

                        } else {
                            Image(
                                bitmap = BitmapFactory.decodeByteArray(
                                    it.image,
                                    0,
                                    it.image.size
                                ).asImageBitmap(), contentDescription = null,
                                modifier = Modifier.size(50.dp)
                            )
                        }
                        Column {
                            Text(text = it.name)
                            Text(text = it.number)
                            Text(text = it.email)
                        }
                        Icon(imageVector = Icons.Rounded.Delete,
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                state.id.value = it.id
                                state.name.value = it.name
                                state.email.value = it.email
                                state.number.value = it.number
                                state.dob.value = it.dob
                                state.image.value = it.image
                                viewModel.deleteContact()
                            })
                    }

                }
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }

}