package com.aiglesiaspubill.androidavanzadofinal.ui.herolist

import com.aiglesiaspubill.androidavanzadofinal.domain.Hero

sealed class HeroListState {
    data class Succes(val heros: List<Hero>) : HeroListState()
    data class Failure(val error: String?) : HeroListState()
    data class NetworkError(val cade: Int) : HeroListState()

}
