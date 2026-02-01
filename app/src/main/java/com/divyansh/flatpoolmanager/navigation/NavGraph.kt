package com.divyansh.flatpoolmanager.navigation

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.divyansh.flatpoolmanager.data.User
import com.divyansh.flatpoolmanager.repository.AuthRepository
import com.divyansh.flatpoolmanager.repository.FirebaseRepository
import com.divyansh.flatpoolmanager.screens.*
import com.divyansh.flatpoolmanager.viewmodel.AuthViewModel
import com.divyansh.flatpoolmanager.viewmodel.ChatViewModel
import com.divyansh.flatpoolmanager.viewmodel.PoolViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    val authState by authViewModel.authState.collectAsState()
    
    val startDestination = if (authState.isLoggedIn) {
        Screen.Home.route
    } else {
        Screen.Login.route
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Login Screen
        composable(Screen.Login.route) {
            LoginScreen(
                authViewModel = authViewModel,
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        // Home Screen
        composable(Screen.Home.route) {
            val currentUser = authState.currentUser ?: return@composable
            
            val poolViewModel: PoolViewModel = viewModel(
                factory = object : androidx.lifecycle.ViewModelProvider.Factory {
                    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                        @Suppress("UNCHECKED_CAST")
                        return PoolViewModel(
                            firebaseRepository = FirebaseRepository(),
                            currentUser = currentUser
                        ) as T
                    }
                }
            )

            HomeScreen(
                viewModel = poolViewModel,
                currentUser = currentUser,
                onNavigateToChat = {
                    navController.navigate(Screen.Chat.route)
                },
                onNavigateToProfile = {
                    navController.navigate(Screen.Profile.route)
                }
            )
        }

        // Chat Screen
        composable(Screen.Chat.route) {
            val currentUser = authState.currentUser ?: return@composable
            
            val chatViewModel: ChatViewModel = viewModel(
                factory = object : androidx.lifecycle.ViewModelProvider.Factory {
                    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                        @Suppress("UNCHECKED_CAST")
                        return ChatViewModel(
                            currentUser = currentUser
                        ) as T
                    }
                }
            )

            ChatScreen(
                chatViewModel = chatViewModel,
                currentUser = currentUser,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // Profile Screen
        composable(Screen.Profile.route) {
            val currentUser = authState.currentUser ?: return@composable

            ProfileScreen(
                currentUser = currentUser,
                authViewModel = authViewModel,
                onLogout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
