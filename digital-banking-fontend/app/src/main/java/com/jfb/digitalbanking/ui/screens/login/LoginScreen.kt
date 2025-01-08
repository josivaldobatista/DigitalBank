package com.jfb.digitalbanking.ui.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jfb.digitalbanking.data.model.LoginRequest

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginSuccess: () -> Unit // Callback para navegar para o dashboard
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val loginState by viewModel.loginState.collectAsState()

    // Observa o estado do login
    LaunchedEffect(loginState) {
        if (loginState is LoginState.Success) {
            onLoginSuccess() // Navega para o dashboard
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Campo de entrada para o username
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Campo de entrada para a senha
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Botão de login
        Button(
            onClick = {
                val loginRequest = LoginRequest(
                    username = username,
                    password = password
                )
                viewModel.login(loginRequest)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .shadow(4.dp, shape = MaterialTheme.shapes.medium),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            )
        ) {
            Text("Sign in", fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Texto de rodapé
        Text(
            text = "Lorem ipsum",
            color = Color.Gray,
            fontSize = 14.sp
        )
    }
}