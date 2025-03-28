package com.example.nanit.ui.components

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.nanit.core.Constants.GALLERY_LAUNCHER_INPUT

@Composable
fun GalleryLauncherComponent(
    updateImage: (uri: Uri) -> Unit,
    content: @Composable (onClick: () -> Unit) -> Unit
) {
    val context = LocalContext.current

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
            it?.let { uri ->
                context.contentResolver.takePersistableUriPermission(
                    uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
                updateImage(uri)
            }
        }

    content { launcher.launch(GALLERY_LAUNCHER_INPUT) }
}