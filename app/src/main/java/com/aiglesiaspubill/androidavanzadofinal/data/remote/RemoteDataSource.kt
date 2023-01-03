package com.aiglesiaspubill.androidavanzadofinal.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class RemoteDataSource {

    //Crear conexion con APIDRAGONBALL
    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private var retrofit = Retrofit.Builder()
        .baseUrl("https://dragonball.keepcoding.education")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private var api: DragonBallAPI = retrofit.create(DragonBallAPI::class.java)

    suspend fun getBootcamps() {
        api.getBootcamps()
    }
}