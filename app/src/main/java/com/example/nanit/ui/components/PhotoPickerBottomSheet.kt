package com.example.nanit.ui.components

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.nanit.R
import com.example.nanit.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoPickerBottomSheet(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    updateImage: (uri: Uri?) -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        Column(modifier = Modifier.padding(Dimens.screenPadding)) {

            GalleryLauncherComponent(
                updateImage = updateImage
            ) { onClick ->
                Text(
                    modifier = Modifier
                        .padding(Dimens.paddingMedium)
                        .fillMaxWidth()
                        .clickable { onClick() },
                    text = stringResource(R.string.all_select_from_gallery_button)
                )
            }

            CameraLauncherComponent(
                updateImage = updateImage
            ) { onClick ->
                Text(
                    modifier = Modifier
                        .padding(Dimens.paddingMedium)
                        .fillMaxWidth()
                        .clickable { onClick() },
                    text = stringResource(R.string.details_take_a_photo_button)
                )
            }
        }
    }
}