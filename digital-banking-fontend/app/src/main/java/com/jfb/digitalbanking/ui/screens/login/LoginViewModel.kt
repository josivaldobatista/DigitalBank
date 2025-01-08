package com.jfb.digitalbanking.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jfb.digitalbanking.data.model.LoginRequest
import com.jfb.digitalbanking.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val token: String) : LoginState()
    data class Error(val message: String) : LoginState()
}

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> get() = _loginState

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            val result = loginUseCase.execute(loginRequest)
            _loginState.value = when {
                result.isSuccess -> LoginState.Success(result.getOrNull()!!.token)
                else -> LoginState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
            }
        }
    }
}