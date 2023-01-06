package com.aiglesiaspubill.androidavanzadofinal.data

import com.aiglesiaspubill.androidavanzadofinal.domain.Hero

sealed class LoginState {
    data class Succes(val token: String) : LoginState()
    data class Failure(val error: String?) : LoginState()
    data class NetworkError(val cade: Int) : LoginState()
    object loading: LoginState()
}
