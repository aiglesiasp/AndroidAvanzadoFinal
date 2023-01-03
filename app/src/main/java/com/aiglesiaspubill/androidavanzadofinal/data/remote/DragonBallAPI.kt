package com.aiglesiaspubill.androidavanzadofinal.data.remote

import com.aiglesiaspubill.androidavanzadofinal.data.remote.request.HerosRequest
import com.aiglesiaspubill.androidavanzadofinal.domain.Bootcamp
import com.aiglesiaspubill.androidavanzadofinal.domain.Hero
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface DragonBallAPI {

    //LLAMADA A BOOTCAMPS DE LA API DE DRAGONBALL
    @GET("/api/data/bootcamps")
    suspend fun getBootcamps(): List<Bootcamp>

    //LLAMADA A HEROES DE LA API DE DRAGONBALL
    @POST("/api/heros/all")
    @Headers("Authorization: Bearer eyJraWQiOiJwcml2YXRlIiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJpZGVudGlmeSI6IkM3QTZBRENFLUM3MjUtNDlFRi04MEFDLTMxNDVCODkxQzg5NCIsImV4cGlyYXRpb24iOjY0MDkyMjExMjAwLCJlbWFpbCI6ImFpZ2xlc2lhc3B1YmlsbEBnbWFpbC5jb20ifQ.NjSKR-UPBTVSNIKunr8QPjwUiZJcnUObOv0pYG28Avc")
    suspend fun getHeros(@Body herosRequest: HerosRequest): List<Hero>


}