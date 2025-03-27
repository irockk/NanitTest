package com.example.nanit.feature.details.presenation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.example.nanit.feature.birthday.presentation.goToBirthdayScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsRoute(
    navController: NavController
) {
    val viewModel = koinViewModel<DetailsViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    DetailsScreen(
        uiState = uiState,
        updateImage = viewModel::updateImage,
        updateName = viewModel::updateName,
        updateBirthday = viewModel::updateBirthday,
        saveData = viewModel::saveUser,
        goToBirthday = navController::goToBirthdayScreen
    )
}