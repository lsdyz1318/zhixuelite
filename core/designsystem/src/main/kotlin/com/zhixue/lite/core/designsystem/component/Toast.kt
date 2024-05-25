package com.zhixue.lite.core.designsystem.component

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

@Composable
fun Toast(
    message: String,
    onMessageShown: () -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(message) {
        if (message.isNotEmpty()) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            onMessageShown()
        }
    }
}