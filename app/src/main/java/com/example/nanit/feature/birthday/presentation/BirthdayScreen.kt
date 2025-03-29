package com.example.nanit.feature.birthday.presentation

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.zIndex
import androidx.core.content.FileProvider
import com.example.nanit.R
import com.example.nanit.feature.birthday.presentation.components.BaseBirthdayScreen
import com.example.nanit.feature.birthday.presentation.models.BirthdayTheme
import com.example.nanit.feature.birthday.presentation.models.toStringRes
import dev.shreyaspatil.capturable.capturable
import dev.shreyaspatil.capturable.controller.rememberCaptureController
import kotlinx.coroutines.launch
import java.io.File

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BirthdayScreen(
    uiState: BirthdayState,
    updateImage: (uri: Uri?) -> Unit,
    goBack: () -> Unit
) {
    val randomScreenIndex = remember { (0..2).random() }
    val captureController = rememberCaptureController()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val isVisible = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .zIndex(1f)
            .capturable(captureController)
    ) {
        if (!isVisible.value) {
            when (randomScreenIndex) {
                0 -> {
                    BaseBirthdayScreen(
                        modifier = Modifier.fillMaxSize(),
                        birthdayTheme = BirthdayTheme.BLUE,
                        name = uiState.name,
                        ageText = uiState.ageUnit?.toStringRes(uiState.ageNumber)
                            ?.let { stringResource(it) },
                        ageNumber = uiState.ageNumber,
                        photo = uiState.photo,
                        updateImage = updateImage
                    )
                }

                1 -> {
                    BaseBirthdayScreen(
                        modifier = Modifier.fillMaxSize(),
                        birthdayTheme = BirthdayTheme.GREEN,
                        name = uiState.name,
                        ageText = uiState.ageUnit?.toStringRes(uiState.ageNumber)
                            ?.let { stringResource(it) },
                        ageNumber = uiState.ageNumber,
                        photo = uiState.photo,
                        updateImage = updateImage
                    )
                }

                else -> {
                    BaseBirthdayScreen(
                        modifier = Modifier.fillMaxSize(),
                        birthdayTheme = BirthdayTheme.YELLOW,
                        name = uiState.name,
                        ageText = uiState.ageUnit?.toStringRes(uiState.ageNumber)
                            ?.let { stringResource(it) },
                        ageNumber = uiState.ageNumber,
                        photo = uiState.photo,
                        updateImage = updateImage
                    )
                }
            }
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .zIndex(2f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = goBack) {
            Icon(
                modifier = Modifier.fillMaxSize(),
                painter = rememberVectorPainter(Icons.Filled.Clear),
                contentDescription = null
            )
        }

        TextButton(onClick = {
            coroutineScope.launch {
                isVisible.value = true
                val bitmapAsync = captureController.captureAsync()
                try {
                    val bitmap = bitmapAsync.await()
                    val uri = bitmap.asAndroidBitmap().toUri(context)
                    val sendIntent: Intent =
                        Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_STREAM, uri)
                            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                            type = "image/png"
                        }
                    context.startActivity(Intent.createChooser(sendIntent, null))
                } catch (error: Throwable) {
                    //TODO show snackbar
                }
            }
        }) {
            Text(text = stringResource(R.string.birthday_share_button))
        }
    }
}

fun Bitmap.toUri(context: Context): Uri {
    val file = File(context.cacheDir, "image.png")
    file.outputStream().use { this.compress(Bitmap.CompressFormat.PNG, 100, it) }

    return FileProvider.getUriForFile(
        context,
        context.applicationContext.packageName + ".provider",
        file
    )
}


