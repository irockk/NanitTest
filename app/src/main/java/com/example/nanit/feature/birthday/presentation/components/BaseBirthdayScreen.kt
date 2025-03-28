package com.example.nanit.feature.birthday.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionOnScreen
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.nanit.R
import com.example.nanit.ui.theme.BlueDark
import com.example.nanit.ui.theme.BlueLight
import com.example.nanit.ui.theme.Typography

@Composable
fun BaseBirthdayScreen(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    contentColor: Color,
    name: String,
    bgImage: Painter,
    ageNumberImage: Painter
) {
    val yPhotoEndOffset = remember { mutableStateOf(IntOffset.Zero) }

    Column(
        modifier = modifier
            .background(backgroundColor)
            .padding(horizontal = 54.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Spacer(Modifier.weight(1f))

        AgeContent(
            name = name,
            ageNumber = ageNumberImage
        )

        Spacer(Modifier.weight(1f))

        Image(
            modifier = Modifier
                .border(6.dp, contentColor, CircleShape)
                .onGloballyPositioned {
                    yPhotoEndOffset.value = IntOffset(
                        0, (it.positionOnScreen().y + it.size.height).toInt()
                    )
                },
            painter = painterResource(R.drawable.img_profile_default_blue),
            contentDescription = null
        )

        Spacer(Modifier.height(148.dp))
    }

    Image(
        modifier = Modifier
            .zIndex(2f)
            .padding(top = 16.dp)
            .fillMaxWidth()
            .offset { yPhotoEndOffset.value },
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
private fun AgeContent(
    modifier: Modifier = Modifier,
    name: String,
    ageNumber: Painter
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.birthday_name_text, name).uppercase(),
            style = Typography.titleLarge
        )

        Spacer(Modifier.height(14.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(R.drawable.ic_left_swirls),
                contentDescription = null
            )

            Spacer(Modifier.width(22.dp))

            Image(
                painter = ageNumber,
                contentDescription = null
            )

            Spacer(Modifier.width(22.dp))

            Icon(
                painter = painterResource(R.drawable.ic_right_swirls),
                contentDescription = null
            )
        }

        Spacer(Modifier.height(14.dp))

        Text(
            text = "MONTH OLD!",
            style = Typography.titleMedium
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
        name = "Ira",
        bgImage = painterResource(R.drawable.img_bg_blue),
        ageNumberImage = painterResource(R.drawable.img_number_0)
    )
}