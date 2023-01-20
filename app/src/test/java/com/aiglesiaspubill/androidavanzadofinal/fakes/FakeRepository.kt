package com.aiglesiaspubill.androidavanzadofinal.fakes

import com.aiglesiaspubill.androidavanzadofinal.data.Repository
import com.aiglesiaspubill.androidavanzadofinal.domain.Location
import com.aiglesiaspubill.androidavanzadofinal.ui.detail.DetailState
import com.aiglesiaspubill.androidavanzadofinal.ui.herolist.HeroListState
import com.aiglesiaspubill.androidavanzadofinal.ui.login.LoginState
import com.aiglesiaspubill.androidavanzadofinal.utils.generateHero
import com.aiglesiaspubill.androidavanzadofinal.utils.generateHeros
import com.aiglesiaspubill.androidavanzadofinal.utils.generateLocations
import com.aiglesiaspubill.androidavanzadofinal.utils.generateToken

class FakeRepository : Repository {
    override suspend fun getHeroes(): HeroListState {
        return HeroListState.Succes(generateHeros())
    }

    override suspend fun getHeroesWithCache(): HeroListState {
        return HeroListState.Succes(generateHeros())
    }

    override suspend fun getToken(): LoginState {
        return LoginState.Succes(generateToken())
    }

    override suspend fun getHeroDetail(name: String): DetailState {
        return DetailState.Succes(generateHero().first())
    }

    override suspend fun getLocations(heroId: String): List<Location> {
        return generateLocations()
    }

    override suspend fun changeFavorite(id: String) {
    }
}