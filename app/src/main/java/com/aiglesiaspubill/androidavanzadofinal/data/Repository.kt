package com.aiglesiaspubill.androidavanzadofinal.data

import com.aiglesiaspubill.androidavanzadofinal.domain.Bootcamp
import com.aiglesiaspubill.androidavanzadofinal.domain.Hero

interface Repository {
    suspend fun getHeroes(): HeroListState
    suspend fun getHeroesWithCache(): HeroListState
    suspend fun getToken(): LoginState
}
