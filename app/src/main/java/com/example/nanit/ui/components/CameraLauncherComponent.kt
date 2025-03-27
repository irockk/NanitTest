package com.example.nanit.ui.components

import android.Manifest
import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.FileProvider
import com.example.nanit.R
import java.io.File
import java.util.Objects

@Composable
fun CameraLauncherComponent(
    modifier: Modifier = Modifier,
    updateImage: (uri: Uri) -> Unit
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
    ) {
        if (it) {
            cameraLauncher.launch(uri)
        } else {
            //TODO show error snackbar
        }
    }

    Button(
        modifier = modifier,
        onClick = { permissionLauncher.launch(Manifest.permission.CAMERA) }) {
        Text(text = stringResource(R.string.details_take_a_photo_button))
    }
}

private fun Context.createImageFile(): File {
    val image = File.createTempFile(
        "profile_", ".jpg", this.cacheDir
    )
    return image
}