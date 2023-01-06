package com.aiglesiaspubill.androidavanzadofinal.data.remote

import com.aiglesiaspubill.androidavanzadofinal.data.remote.request.HerosRequest
import com.aiglesiaspubill.androidavanzadofinal.data.remote.response.HeroRemote
import com.aiglesiaspubill.androidavanzadofinal.domain.Bootcamp
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: DragonBallAPI) : RemoteDataSource {


    //FUNCION OBTENR HEROES CON ERRORES - LLAMADA A LA API
    override suspend fun getHeros(): Result<List<HeroRemote>> {
        return runCatching { api.getHeros(HerosRequest()) }
    }

    override suspend fun getToken(): Result<String> {
        return runCatching { api.getToken() }
    }
}