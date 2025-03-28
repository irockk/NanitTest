package com.example.nanit.feature.birthday.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.nanit.R
import com.example.nanit.ui.theme.Dimens
import com.example.nanit.ui.theme.Typography

@Composable
fun AgeContent(
    modifier: Modifier = Modifier,
    name: String,
    ageNumbers: List<Painter>,
    ageText: String,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.birthday_name_text, name).uppercase(),
            style = Typography.titleLarge,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(Dimens.paddingMedium))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(R.drawable.ic_left_swirls),
                contentDescription = null
            )

            Spacer(Modifier.width(Dimens.paddingBig))

            ageNumbers.forEach { ageNumber ->
                Image(
                    modifier = Modifier.padding(Dimens.paddingExtraSmall),
                    painter = ageNumber,
                    contentDescription = null
                )
            }

            Spacer(Modifier.width(Dimens.paddingBig))

            Icon(
                painter = painterResource(R.drawable.ic_right_swirls),
                contentDescription = null
            )
        }

        Spacer(Modifier.height(Dimens.paddingMedium))

        Text(
            text = ageText,
            style = Typography.titleMedium
        )
    }
}