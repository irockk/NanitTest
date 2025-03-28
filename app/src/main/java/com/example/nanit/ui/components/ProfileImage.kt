package com.example.nanit.ui.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest

@Composable
fun ProfileImage(
    modifier: Modifier = Modifier,
    uri: Uri?
) {
    SubcomposeAsyncImage(
        modifier = modifier.clip(CircleShape),
        model = ImageRequest.Builder(LocalContext.current).data(uri).build(),
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