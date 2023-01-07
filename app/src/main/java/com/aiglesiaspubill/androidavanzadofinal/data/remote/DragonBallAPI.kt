package com.aiglesiaspubill.androidavanzadofinal.data.remote

import com.aiglesiaspubill.androidavanzadofinal.data.remote.request.HerosRequest
import com.aiglesiaspubill.androidavanzadofinal.data.remote.request.LocationRequest
import com.aiglesiaspubill.androidavanzadofinal.data.remote.response.HeroRemote
import com.aiglesiaspubill.androidavanzadofinal.data.remote.response.LocationRemote
import retrofit2.http.Body
import retrofit2.http.POST

interface DragonBallAPI {

    //LLAMADA A BOOTCAMPS DE LA API DE DRAGONBALL
    @POST("/api/auth/login")
    suspend fun getToken(): String

    //LLAMADA A HEROES DE LA API DE DRAGONBALL CON EXCEPCION
    @POST("/api/heros/all")
    suspend fun getHeros(@Body herosRequest: HerosRequest): List<HeroRemote>

    //LLAMADA A HEROES DE LA API DE DRAGONBALL CON EXCEPCION
    @POST("/api/heros/all")
    suspend fun getHerosDetail(@Body herosRequest: HerosRequest): List<HeroRemote>

    //LLAMADA A HEROES DE LA API DE DRAGONBALL CON EXCEPCION
    @POST("/api/heros/locations")
    suspend fun getLocations(@Body locationRequest: LocationRequest): List<LocationRemote>

}