package com.aiglesiaspubill.androidavanzadofinal.data

import com.aiglesiaspubill.androidavanzadofinal.domain.Location
import com.aiglesiaspubill.androidavanzadofinal.ui.detail.DetailState
import com.aiglesiaspubill.androidavanzadofinal.ui.herolist.HeroListState
import com.aiglesiaspubill.androidavanzadofinal.ui.login.LoginState

interface Repository {
    suspend fun getHeroes(): HeroListState
    suspend fun getHeroesWithCache(): HeroListState
    suspend fun getToken(): LoginState
    suspend fun getHeroDetail(name: String): DetailState
    suspend fun getLocations(heroId: String): List<Location>
}
