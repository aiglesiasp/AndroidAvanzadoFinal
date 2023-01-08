package com.aiglesiaspubill.androidavanzadofinal.fakes

import com.aiglesiaspubill.androidavanzadofinal.data.remote.DragonBallAPI
import com.aiglesiaspubill.androidavanzadofinal.data.remote.RemoteDataSource
import com.aiglesiaspubill.androidavanzadofinal.data.remote.response.HeroRemote
import com.aiglesiaspubill.androidavanzadofinal.data.remote.response.LocationRemote
import com.aiglesiaspubill.androidavanzadofinal.domain.Hero
import com.aiglesiaspubill.androidavanzadofinal.utils.generateHeroRemote
import com.aiglesiaspubill.androidavanzadofinal.utils.generateHerosRemote
import com.aiglesiaspubill.androidavanzadofinal.utils.generateRemoteLocations

class FakeRemoteDataSource(val hero: Hero): RemoteDataSource {
    override suspend fun getHeros(): Result<List<HeroRemote>> {
            return Result.success(generateHerosRemote())
        }

    override suspend fun getToken(): Result<String> {
        return Result.success("")
    }

    override suspend fun getHeroDetail(name: String): Result<HeroRemote?> {
        return Result.success(generateHeroRemote())
    }

    override suspend fun getLocations(heroId: String): Result<List<LocationRemote>> {
        return Result.success(generateRemoteLocations())
    }

    override suspend fun changeFavorite(id: String) {
    }
}