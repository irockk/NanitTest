package com.example.nanit.feature.birthday.presentation.components

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.zIndex
import com.example.nanit.R
import com.example.nanit.ui.theme.BlueDark
import com.example.nanit.ui.theme.BlueLight
import com.example.nanit.ui.theme.Dimens

@Composable
fun BaseBirthdayScreen(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    contentColor: Color,
    name: String,
    bgImage: Painter,
    ageText: String?,
    ageNumber: Int?,
    defaultPhoto: Painter
) {
    val photoEndOffset = remember { mutableStateOf(IntOffset.Zero) }

    Column(
        modifier = modifier
            .background(backgroundColor)
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
            contentColor = contentColor,
            defaultPhoto = defaultPhoto,
            updatePhotoEndOffset = { photoEndOffset.value = it }
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
        painter = bgImage,
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
        backgroundColor = BlueLight,
        contentColor = BlueDark,
        name = "Ira Ira Superhero SuperIra",
        bgImage = painterResource(R.drawable.img_bg_blue),
        ageText = "years",
        ageNumber = 120,
        defaultPhoto = painterResource(R.drawable.img_profile_default_blue)
    )
}