package com.example.nanit.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun getAppTitle(): String {
    val context = LocalContext.current
    val appName = context.packageManager.getApplicationLabel(context.applicationInfo)
    return appName.toString()
}