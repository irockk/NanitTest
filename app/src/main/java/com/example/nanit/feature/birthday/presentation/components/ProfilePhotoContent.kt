package com.example.nanit.feature.birthday.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionOnScreen
import androidx.compose.ui.unit.IntOffset
import com.example.nanit.ui.theme.Dimens

@Composable
fun ProfilePhotoContent(
    modifier: Modifier = Modifier,
    contentColor: Color,
    defaultPhoto: Painter,
    updatePhotoEndOffset: (newValue: IntOffset) -> Unit
) {
    Image(
        modifier = modifier
            .border(Dimens.borderWidth, contentColor, CircleShape)
            .onGloballyPositioned {
                updatePhotoEndOffset(
                    IntOffset(
                        0, (it.positionOnScreen().y + it.size.height).toInt()
                    )
                )
            },
        painter = defaultPhoto,
        contentDescription = null
    )
}