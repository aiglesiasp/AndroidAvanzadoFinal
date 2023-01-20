package com.aiglesiaspubill.androidavanzadofinal.data.remote

import com.aiglesiaspubill.androidavanzadofinal.data.remote.request.FavoriteRequest
import com.aiglesiaspubill.androidavanzadofinal.data.remote.request.HerosRequest
import com.aiglesiaspubill.androidavanzadofinal.data.remote.request.LocationRequest
import com.aiglesiaspubill.androidavanzadofinal.data.remote.response.HeroRemote
import com.aiglesiaspubill.androidavanzadofinal.data.remote.response.LocationRemote
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: DragonBallAPI) : RemoteDataSource {

    override suspend fun getHeros(): Result<List<HeroRemote>> {
        return runCatching { api.getHeros(HerosRequest()) }
    }

    override suspend fun getToken(): Result<String> {
        return runCatching { api.getToken() }
    }

    override suspend fun getHeroDetail(name: String): Result<HeroRemote?> {
        return runCatching { api.getHerosDetail(HerosRequest(name)).firstOrNull() }
    }

    override suspend fun getLocations(heroId: String): Result<List<LocationRemote>> {
        return runCatching { api.getLocations(LocationRequest(heroId)) }
    }

    override suspend fun changeFavorite(id: String) {
        kotlin.runCatching { api.changeFavorite(FavoriteRequest(id)) }
    }
}

