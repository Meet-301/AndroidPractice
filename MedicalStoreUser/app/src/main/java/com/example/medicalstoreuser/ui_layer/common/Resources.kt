package com.example.medicalstoreuser.ui_layer.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

@Composable
fun MultiColorText(firstText: String, secondText: String, firstColor: Color, secondColor: Color) {
    val annotatedText = buildAnnotatedString {
        withStyle(style = SpanStyle(color = firstColor)) {
            append(firstText)
        }
        withStyle(style = SpanStyle(color = secondColor)) {
            append(secondText)
        }
    }
}