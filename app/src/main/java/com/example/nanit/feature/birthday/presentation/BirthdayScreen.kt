package com.example.nanit.feature.birthday.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.zIndex
import com.example.nanit.R
import com.example.nanit.feature.birthday.presentation.components.BaseBirthdayScreen
import com.example.nanit.feature.birthday.presentation.models.toStringRes
import com.example.nanit.ui.theme.BlueDark
import com.example.nanit.ui.theme.BlueLight
import com.example.nanit.ui.theme.GreenDark
import com.example.nanit.ui.theme.GreenLight
import com.example.nanit.ui.theme.YellowDark
import com.example.nanit.ui.theme.YellowLight

@Composable
fun BirthdayScreen(
    uiState: BirthdayState,
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
                backgroundColor = BlueLight,
                contentColor = BlueDark,
                name = uiState.name,
                bgImage = painterResource(R.drawable.img_bg_blue),
                ageText = uiState.ageUnit?.toStringRes(uiState.ageNumber)
                    ?.let { stringResource(it) },
                ageNumber = uiState.ageNumber,
                defaultPhoto = painterResource(R.drawable.img_profile_default_blue)
            )
        }

        1 -> {
            BaseBirthdayScreen(
                modifier = Modifier.fillMaxSize(),
                backgroundColor = GreenLight,
                contentColor = GreenDark,
                name = uiState.name,
                bgImage = painterResource(R.drawable.img_bg_green),
                ageText = uiState.ageUnit?.toStringRes(uiState.ageNumber)
                    ?.let { stringResource(it) },
                ageNumber = uiState.ageNumber,
                defaultPhoto = painterResource(R.drawable.img_profile_default_green)
            )
        }

        else -> {
            BaseBirthdayScreen(
                modifier = Modifier.fillMaxSize(),
                backgroundColor = YellowLight,
                contentColor = YellowDark,
                name = uiState.name,
                bgImage = painterResource(R.drawable.img_bg_yellow),
                ageText = uiState.ageUnit?.toStringRes(uiState.ageNumber)
                    ?.let { stringResource(it) },
                ageNumber = uiState.ageNumber,
                defaultPhoto = painterResource(R.drawable.img_profile_default_yellow)
            )
        }
    }
}