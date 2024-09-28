package com.example.permissionspractice1

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberMultiplePermissionsState


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Test2() {

    val permissions = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.RECORD_AUDIO
        )
    )

    Column {
        if (permissions.allPermissionsGranted) {
            Text(text = "Permissions Granted")
        } else {
            Button(onClick = {
                permissions.launchMultiplePermissionRequest()
            }) {
                Text(text = "Give Permission")
            }
        }
    }

    permissions.permissions.forEach {
        when(it.status) {
            is PermissionStatus.Granted -> {
                Text(text = "Permission Granted")
            }
            is PermissionStatus.Denied -> {
                Text(text = "Permission Denied")
            }
        }
    }

}