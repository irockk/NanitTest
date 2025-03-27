package com.example.nanit.feature.details.presenation

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import com.example.nanit.R
import com.example.nanit.core.toFormatedDate
import com.example.nanit.ui.components.getAppTitle
import com.example.nanit.ui.theme.Dimens

@Composable
fun DetailsScreen(uiState: DetailsState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.screenPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = getAppTitle())
        Text(text = stringResource(R.string.details_name, uiState.name))
        Text(text = stringResource(R.string.details_birthday, uiState.birthday.toFormatedDate()))

        ProfileImage(
            modifier = Modifier
                .padding(horizontal = Dimens.paddingBig)
                .aspectRatio(1f),
            uri = uiState.image
        )

        Button(
            onClick = { /*TODO Navigate to birthday screen */ },
            enabled = uiState.isButtonEnabled
        ) {
            Text(stringResource(R.string.details_button_text))
        }
    }
}

@Composable
private fun ProfileImage(
    modifier: Modifier = Modifier,
    uri: Uri?
) {
    SubcomposeAsyncImage(
        modifier = modifier.clip(CircleShape),
        model = ImageRequest
            .Builder(LocalContext.current)
            .data(uri)
            .build(),
        contentDescription = null,
        error = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    painter = rememberVectorPainter(Icons.Filled.AccountCircle),
                    contentDescription = null
                )
            }
        },
        loading = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        },
        contentScale = ContentScale.Crop
    )
}