package com.example.nanit.ui.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.nanit.R
import com.example.nanit.core.Constants.GALLERY_LAUNCHER_INPUT

@Composable
fun GalleryLauncherComponent(
    modifier: Modifier = Modifier,
    updateImage: (uri: Uri) -> Unit
) {
    val galleryLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent(),
            onResult = { newUri ->
                newUri?.let {
                    updateImage(newUri)
                }
            })

    Button(modifier = modifier, onClick = { galleryLauncher.launch(GALLERY_LAUNCHER_INPUT) }) {
        Text(text = stringResource(R.string.details_select_from_gallery_button))
    }
}