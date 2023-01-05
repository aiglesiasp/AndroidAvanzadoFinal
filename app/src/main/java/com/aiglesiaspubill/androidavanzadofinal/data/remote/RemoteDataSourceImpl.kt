package com.aiglesiaspubill.androidavanzadofinal.data.remote

import com.aiglesiaspubill.androidavanzadofinal.data.remote.request.HerosRequest
import com.aiglesiaspubill.androidavanzadofinal.data.remote.response.HeroRemote
import com.aiglesiaspubill.androidavanzadofinal.domain.Bootcamp

class RemoteDataSourceImpl (private val api: DragonBallAPI) : RemoteDataSource {

    //FUNCION OBTENR BOOTCAMPS - LLAMADA A LA API
    override suspend fun getBootcamps(): List<Bootcamp> {
        return api.getBootcamps()
    }

    //FUNCION OBTENR HEROES - LLAMADA A LA API
    override suspend fun getHeroes(): List<HeroRemote> {
        return api.getHeros(HerosRequest())
    }

}