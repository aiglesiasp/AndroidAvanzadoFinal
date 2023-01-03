package com.aiglesiaspubill.androidavanzadofinal.data.remote

import com.aiglesiaspubill.androidavanzadofinal.domain.Bootcamp
import retrofit2.http.GET

interface DragonBallAPI {

    //LLAMADA A BOOTCAMPS DE LA API DE DRAGONBALL
    @GET("/api/data/bootcamps")
    suspend fun getBootcamps(): List<Bootcamp>


}