package com.aiglesiaspubill.androidavanzadofinal.data.remote

import com.aiglesiaspubill.androidavanzadofinal.data.remote.request.FavoriteRequest
import com.aiglesiaspubill.androidavanzadofinal.data.remote.request.HerosRequest
import com.aiglesiaspubill.androidavanzadofinal.data.remote.request.LocationRequest
import com.aiglesiaspubill.androidavanzadofinal.data.remote.response.HeroRemote
import com.aiglesiaspubill.androidavanzadofinal.data.remote.response.LocationRemote
import retrofit2.http.Body
import retrofit2.http.POST

interface DragonBallAPI {

    @POST("/api/auth/login")
    suspend fun getToken(): String

    @POST("/api/heros/all")
    suspend fun getHeros(@Body herosRequest: HerosRequest): List<HeroRemote>

    @POST("/api/heros/all")
    suspend fun getHerosDetail(@Body herosRequest: HerosRequest): List<HeroRemote>

    @POST("/api/heros/locations")
    suspend fun getLocations(@Body locationRequest: LocationRequest): List<LocationRemote>

    @POST("/api/data/herolike")
    suspend fun changeFavorite(@Body favoriteRequest: FavoriteRequest)

}