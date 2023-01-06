package com.aiglesiaspubill.androidavanzadofinal.data.remote

import com.aiglesiaspubill.androidavanzadofinal.data.remote.request.HerosRequest
import com.aiglesiaspubill.androidavanzadofinal.data.remote.response.HeroRemote
import com.aiglesiaspubill.androidavanzadofinal.domain.Bootcamp
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface DragonBallAPI {

    //LLAMADA A BOOTCAMPS DE LA API DE DRAGONBALL
    @POST("/api/auth/login")
    suspend fun login(): String

    //LLAMADA A BOOTCAMPS DE LA API DE DRAGONBALL
    @GET("/api/data/bootcamps")
    suspend fun getBootcamps(): List<Bootcamp>

    //LLAMADA A HEROES DE LA API DE DRAGONBALL CON EXCEPCION
    @POST("/api/heros/all")
    suspend fun getHerosWithException(): List<HeroRemote>

    //LLAMADA A HEROES DE LA API DE DRAGONBALL
    @POST("/api/heros/all")
    suspend fun getHeros(@Body herosRequest: HerosRequest): List<HeroRemote>

}