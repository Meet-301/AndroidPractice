package com.example.camerax

import android.content.ContentValues
import android.content.Context
import android.provider.MediaStore
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Composable
fun CameraScreen(modifier: Modifier = Modifier) {

    val lens = CameraSelector.LENS_FACING_BACK

    val context = LocalContext.current

    val lifeCycleOwner = LocalLifecycleOwner.current

    val preview = Preview.Builder().build()

    // Compose Preview
    val previewView = remember { PreviewView(context) }

    // Building the camera selector
    val cameraSelector = CameraSelector.Builder().requireLensFacing(lens).build()

    val capture = remember { ImageCapture.Builder().build() }
    
    LaunchedEffect(lens) {

        val cameraProvider = context.getCameraProvider()

        cameraProvider.unbindAll()

        cameraProvider.bindToLifecycle(
            lifeCycleOwner,
            cameraSelector,
            preview,
            capture)

        preview.surfaceProvider = previewView.surfaceProvider

    }

    Box(modifier = modifier.fillMaxSize()) {
        AndroidView(factory = { previewView })

        Button(onClick = { takePhoto(capture, context) }) {
            Text(text = "Capture")
        }
    }

}

private suspend fun Context.getCameraProvider(): ProcessCameraProvider = suspendCoroutine {

    val listener = ProcessCameraProvider.getInstance(this).also { cameraProvider ->
        cameraProvider.addListener({
            it.resume(cameraProvider.get())
        }, mainExecutor)
    }

}

private fun takePhoto(imageCapture: ImageCapture, context: Context) {

    val name = "CameraX_${System.currentTimeMillis()}.jpg"

    val contentValue = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, name)
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
        put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
    }

    val outputOption = ImageCapture.OutputFileOptions.Builder(
        context.contentResolver,
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        contentValue
    ).build()

    imageCapture.takePicture(
        outputOption,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                Toast.makeText(context,"Image Saved Successfully",Toast.LENGTH_SHORT).show()
            }

            override fun onError(exception: ImageCaptureException) {
                Toast.makeText(context,"Image Saving Failed",Toast.LENGTH_SHORT).show()
            }

        }
    )

}