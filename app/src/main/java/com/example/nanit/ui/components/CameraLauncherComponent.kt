package com.example.nanit.ui.components

import android.Manifest
import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import java.io.File
import java.util.Objects

@Composable
fun CameraLauncherComponent(
    updateImage: (uri: Uri) -> Unit,
    content: @Composable (onClick: () -> Unit) -> Unit
) {
    val context = LocalContext.current
    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context), context.applicationContext.packageName + ".provider", file
    )

    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
        updateImage(uri)
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted || Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            cameraLauncher.launch(uri)
        } else {
            //TODO show error snackbar
        }
    }

    content { permissionLauncher.launch(Manifest.permission.CAMERA) }
}

private fun Context.createImageFile(): File {
    val image = File.createTempFile(
        "profile_", ".jpg", this.cacheDir
    )
    return image
}