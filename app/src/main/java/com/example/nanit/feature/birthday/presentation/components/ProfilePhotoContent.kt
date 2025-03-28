package com.example.nanit.feature.birthday.presentation.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionOnScreen
import androidx.compose.ui.unit.IntOffset
import com.example.nanit.ui.components.ProfileImage
import com.example.nanit.ui.theme.Dimens

@Composable
fun ProfilePhotoContent(
    modifier: Modifier = Modifier,
    contentColor: Color,
    defaultPhoto: Painter,
    buttonIcon: Painter,
    photo: Uri?,
    updatePhotoEndOffset: (newValue: IntOffset) -> Unit,
    onUpdatePhotoClick: () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        val innerModifier = Modifier
            .border(Dimens.borderWidth, contentColor, CircleShape)
            .onGloballyPositioned {
                updatePhotoEndOffset(
                    IntOffset(
                        0, (it.positionOnScreen().y + it.size.height).toInt()
                    )
                )
            }
        if (photo != null) {
            ProfileImage(
                modifier = modifier.then(innerModifier),
                uri = photo
            )
        } else {
            Image(
                modifier = modifier.then(innerModifier),
                painter = defaultPhoto,
                contentDescription = null
            )
        }

        Image(
            modifier = Modifier
                .offset(
                    x = (Dimens.profilePictureSize / 2.8f),
                    y = (-Dimens.profilePictureSize / 2.8f)
                )
                .clickable { onUpdatePhotoClick() },
            painter = buttonIcon,
            contentDescription = null
        )
    }
}