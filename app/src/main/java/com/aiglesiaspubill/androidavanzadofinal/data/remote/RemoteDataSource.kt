package com.aiglesiaspubill.androidavanzadofinal.data.remote

import com.aiglesiaspubill.androidavanzadofinal.data.remote.response.HeroRemote
import com.aiglesiaspubill.androidavanzadofinal.domain.Bootcamp

interface RemoteDataSource {
    suspend fun getHeros(): Result<List<HeroRemote>>
    suspend fun getToken(): Result<String>
    suspend fun getHeroDetail(name: String): Result<HeroRemote?>
}