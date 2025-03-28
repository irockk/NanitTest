package com.example.nanit.feature.birthday.presentation

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.zIndex
import com.example.nanit.feature.birthday.presentation.components.BaseBirthdayScreen
import com.example.nanit.feature.birthday.presentation.models.BirthdayTheme
import com.example.nanit.feature.birthday.presentation.models.toStringRes

@Composable
fun BirthdayScreen(
    uiState: BirthdayState,
    updateImage: (uri: Uri?) -> Unit,
    goBack: () -> Unit
) {
    val randomScreenIndex = remember { (0..2).random() }

    IconButton(
        modifier = Modifier.zIndex(2f),
        onClick = goBack
    ) {
        Icon(
            modifier = Modifier.fillMaxSize(),
            painter = rememberVectorPainter(Icons.Filled.Clear),
            contentDescription = null
        )
    }

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