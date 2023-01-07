package com.aiglesiaspubill.androidavanzadofinal.ui.login

sealed class LoginState {
    data class Succes(val token: String) : LoginState()
    data class Failure(val error: String?) : LoginState()
    data class NetworkError(val cade: Int) : LoginState()
    object loading: LoginState()
}
