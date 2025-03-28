package com.example.nanit.feature.birthday.presentation.models

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.nanit.R
import com.example.nanit.ui.theme.BlueDark
import com.example.nanit.ui.theme.BlueLight
import com.example.nanit.ui.theme.GreenDark
import com.example.nanit.ui.theme.GreenLight
import com.example.nanit.ui.theme.YellowDark
import com.example.nanit.ui.theme.YellowLight

enum class BirthdayTheme(
    val contentColor: Color,
    val backgroundColor: Color,
    @DrawableRes val bgImageRes: Int,
    @DrawableRes val defaultPhotoRes: Int,
    @DrawableRes val buttonIconRes: Int,
) {
    BLUE(
        BlueDark,
        BlueLight,
        R.drawable.img_bg_blue,
        R.drawable.img_profile_default_blue,
        R.drawable.ic_photo_blue
    ),
    GREEN(
        GreenDark,
        GreenLight,
        R.drawable.img_bg_green,
        R.drawable.img_profile_default_green,
        R.drawable.ic_photo_green
    ),
    YELLOW(
        YellowDark,
        YellowLight,
        R.drawable.img_bg_yellow,
        R.drawable.img_profile_default_yellow,
        R.drawable.ic_photo_yellow
    )
}