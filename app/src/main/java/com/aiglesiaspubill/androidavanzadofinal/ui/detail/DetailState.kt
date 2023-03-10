package com.aiglesiaspubill.androidavanzadofinal.ui.detail

import com.aiglesiaspubill.androidavanzadofinal.domain.Hero

sealed class DetailState {
    data class Succes(val hero: Hero) : DetailState()
    data class Failure(val error: String?) : DetailState()
    data class NetworkError(val code: Int) : DetailState()

}
