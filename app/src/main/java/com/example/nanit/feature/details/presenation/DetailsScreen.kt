package com.example.nanit.feature.details.presenation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        Text(text = "Name: ${uiState.name}")
        Text(text = "Birthday: ${uiState.birthday.toFormatedDate()}")

        Button(
            onClick = { /*TODO Navigate to birthday screen */ },
            enabled = uiState.isButtonEnabled
        ) {
            Text("Show birthday screen")
        }
    }
}