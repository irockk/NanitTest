package com.example.nanit.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nanit.feature.birthday.presentation.BirthdayRoute
import com.example.nanit.feature.details.presenation.DetailsRoute

@Composable
fun Navigator(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Details.route
    ) {
        composable(route = Screen.Details.route) {
            DetailsRoute(
                navController = navController
            )
        }
        composable(route = Screen.Birthday.route) {
            BirthdayRoute(navController = navController)
        }
    }
}