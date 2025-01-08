package com.jfb.digitalbanking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.jfb.digitalbanking.data.repository.UserRepository
import com.jfb.digitalbanking.domain.usecase.LoginUseCase
import com.jfb.digitalbanking.ui.navigation.AppNavigation
import com.jfb.digitalbanking.ui.screens.login.LoginViewModel
import com.jfb.digitalbanking.ui.theme.DigitalBankingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DigitalBankingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val userRepository = UserRepository()
                    val loginUseCase = LoginUseCase(userRepository)
                    val viewModel = LoginViewModel(loginUseCase)

                    AppNavigation(viewModel = viewModel)
                }
            }
        }
    }
}