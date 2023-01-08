package com.aiglesiaspubill.androidavanzadofinal.data.remote

import com.aiglesiaspubill.androidavanzadofinal.data.remote.response.HeroRemote
import com.aiglesiaspubill.androidavanzadofinal.data.remote.response.LocationRemote

interface RemoteDataSource {
    suspend fun getHeros(): Result<List<HeroRemote>>
    suspend fun getToken(): Result<String>
    suspend fun getHeroDetail(name: String): Result<HeroRemote?>
    suspend fun getLocations(heroId: String): Result<List<LocationRemote>>
    suspend fun getFavorite(id: String)
}