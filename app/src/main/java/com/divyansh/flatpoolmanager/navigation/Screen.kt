package com.divyansh.flatpoolmanager.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
    object Chat : Screen("chat")
    object Profile : Screen("profile")
}
