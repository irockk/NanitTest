package com.example.nanit.feature.birthday.presentation.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.zIndex
import com.example.nanit.R
import com.example.nanit.feature.birthday.presentation.models.BirthdayTheme
import com.example.nanit.ui.components.PhotoPickerBottomSheet
import com.example.nanit.ui.theme.Dimens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseBirthdayScreen(
    modifier: Modifier = Modifier,
    birthdayTheme: BirthdayTheme,
    name: String,
    ageText: String?,
    ageNumber: Int?,
    photo: Uri?,
    updateImage: (uri: Uri?) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    if (sheetState.isVisible) {
        PhotoPickerBottomSheet(
            sheetState = sheetState,
            onDismiss = { coroutineScope.launch { sheetState.hide() } },
            updateImage = { uri ->
                updateImage(uri)
                coroutineScope.launch { sheetState.hide() }
            }
        )
    }

    val photoEndOffset = remember { mutableStateOf(IntOffset.Zero) }

    Column(
        modifier = modifier
            .background(birthdayTheme.backgroundColor)
            .padding(horizontal = Dimens.screenPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Spacer(Modifier.weight(1f))

        if (ageNumber != null && ageText != null) {
            AgeContent(
                name = name,
                ageNumbers = ageNumber.ageNumberToPainters(),
                ageText = stringResource(R.string.birthday_unit_old_text, ageText).uppercase()
            )
        }

        Spacer(Modifier.weight(1f))

        ProfilePhotoContent(
            modifier = Modifier.size(Dimens.profilePictureSize),
            contentColor = birthdayTheme.contentColor,
            defaultPhoto = painterResource(birthdayTheme.defaultPhotoRes),
            buttonIcon = painterResource(birthdayTheme.buttonIconRes),
            photo = photo,
            updatePhotoEndOffset = { photoEndOffset.value = it },
            onUpdatePhotoClick = { coroutineScope.launch { sheetState.show() } }
        )

        Spacer(Modifier.height(Dimens.paddingHuge))
    }

    Image(
        modifier = Modifier
            .zIndex(2f)
            .fillMaxWidth()
            .offset { photoEndOffset.value },
        painter = painterResource(R.drawable.ic_nanit),
        contentDescription = null
    )

    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(birthdayTheme.bgImageRes),
        contentScale = ContentScale.FillWidth,
        alignment = Alignment.BottomCenter,
        contentDescription = null
    )
}

@Composable
private fun Int.ageNumberToPainters(): List<Painter> {
    val context = LocalContext.current
    val digits = this.toString().map { it.toString().toInt() }
    return digits.map { digit ->
        painterResource(
            context.resources.getIdentifier(
                "img_number_$digit",
                "drawable",
                context.packageName
            )
        )
    }
}

@Preview
@Composable
fun BaseBirthdayScreenPreview(modifier: Modifier = Modifier) {
    BaseBirthdayScreen(
        modifier = Modifier.fillMaxSize(),
        birthdayTheme = BirthdayTheme.BLUE,
        name = "Ira Ira Superhero SuperIra",
        ageText = "years",
        ageNumber = 120,
        photo = null,
        updateImage = {}
    )
}