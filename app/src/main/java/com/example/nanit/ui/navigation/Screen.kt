package com.example.nanit.ui.navigation

sealed class Screen(val route: String) {
    data object Details : Screen("details_route")
    data object Birthday : Screen("birthday_route")
}