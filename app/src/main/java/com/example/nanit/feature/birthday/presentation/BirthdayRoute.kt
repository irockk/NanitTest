package com.example.nanit.feature.birthday.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.example.nanit.ui.navigation.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun BirthdayRoute() {
    val viewModel = koinViewModel<BirthdayViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    BirthdayScreen(
        uiState = uiState
    )
}

fun NavController.goToBirthdayScreen() {
    this.navigate(Screen.Birthday.route)
}