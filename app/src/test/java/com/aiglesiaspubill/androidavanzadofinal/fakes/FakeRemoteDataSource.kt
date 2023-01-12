package com.aiglesiaspubill.androidavanzadofinal.fakes

import com.aiglesiaspubill.androidavanzadofinal.data.remote.DragonBallAPI
import com.aiglesiaspubill.androidavanzadofinal.data.remote.RemoteDataSource
import com.aiglesiaspubill.androidavanzadofinal.data.remote.response.HeroRemote
import com.aiglesiaspubill.androidavanzadofinal.data.remote.response.LocationRemote
import com.aiglesiaspubill.androidavanzadofinal.domain.Hero
import com.aiglesiaspubill.androidavanzadofinal.ui.detail.DetailState
import com.aiglesiaspubill.androidavanzadofinal.ui.login.LoginState
import com.aiglesiaspubill.androidavanzadofinal.utils.generateHeroRemote
import com.aiglesiaspubill.androidavanzadofinal.utils.generateHerosRemote
import com.aiglesiaspubill.androidavanzadofinal.utils.generateRemoteLocations
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

class FakeRemoteDataSource(): RemoteDataSource {

    //------------------------------------------------------------------------------------//
    override suspend fun getHeros(): Result<List<HeroRemote>> {
            return Result.success(generateHerosRemote())
            return Result.failure(java.lang.NullPointerException())
        }

    //------------------------------------------------------------------------------------//
    override suspend fun getToken(): Result<String> {
        return Result.success("123456")
        return Result.failure(java.lang.NullPointerException())
    }

    //------------------------------------------------------------------------------------//
    override suspend fun getHeroDetail(name: String): Result<HeroRemote?> {

        return when (name) {
            "SUCCESS" -> Result.success(generateHeroRemote())
            "NETWORK_ERROR" -> Result.failure(HttpException(Response.success(204, {})))
            "NULL" -> Result.failure(java.lang.NullPointerException("Null pointer exception"))
            "SUCCESS_BUT_NULL" -> Result.success(null)
            else -> { Result.failure(java.lang.Exception())}
        }
    }

    //------------------------------------------------------------------------------------//
    override suspend fun getLocations(heroId: String): Result<List<LocationRemote>> {
        return when (heroId) {
            "SUCCESS" -> Result.success(generateRemoteLocations())
            "NETWORK_ERROR" -> Result.failure(HttpException(Response.success(204, {})))
            "NULL" -> Result.failure(java.lang.NullPointerException("Null pointer exception"))
            "SUCCESS_BUT_NULL" -> Result.success(emptyList())
            else -> { Result.failure(java.lang.Exception())}
        }
    }

    //------------------------------------------------------------------------------------//
    override suspend fun changeFavorite(id: String) {
    }
}