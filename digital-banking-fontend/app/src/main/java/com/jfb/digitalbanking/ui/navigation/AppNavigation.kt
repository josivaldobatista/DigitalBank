package com.jfb.digitalbanking.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jfb.digitalbanking.ui.screens.dashboard.DashboardScreen
import com.jfb.digitalbanking.ui.screens.login.LoginScreen
import com.jfb.digitalbanking.ui.screens.login.LoginViewModel

@Composable
fun AppNavigation(viewModel: LoginViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                viewModel = viewModel,
                onLoginSuccess = {
                    navController.navigate("dashboard") {
                        popUpTo("login") { inclusive = true } // Remove a tela de login da pilha
                    }
                }
            )
        }
        composable("dashboard") {
            DashboardScreen()
        }
    }
}