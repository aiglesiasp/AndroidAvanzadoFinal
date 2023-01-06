package com.aiglesiaspubill.androidavanzadofinal.data

import com.aiglesiaspubill.androidavanzadofinal.domain.Bootcamp
import com.aiglesiaspubill.androidavanzadofinal.domain.Hero

interface Repository {
    suspend fun getBootcamps(): List<Bootcamp>
    suspend fun getHeroes(): List<Hero>
    suspend fun getHeroesWithCache(): List<Hero>
    suspend fun getHeroesWithException(): HeroListState
    suspend fun getLogin(): String
}
