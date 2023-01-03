package com.aiglesiaspubill.androidavanzadofinal.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RemoteDataSource {

    private lateinit var retrofit: Retrofit
    private lateinit var api: DragonBallAPI

    //Crear conexion con APIDRAGONBALL
    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        val clientBuilder = OkHttpClient.Builder()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(httpLoggingInterceptor)

        retrofit = Retrofit.Builder()
            .baseUrl("https://dragonball.keepcoding.education")
            .client(clientBuilder.build())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        api = retrofit.create(DragonBallAPI::class.java)

    }

    suspend fun getBootcamps() {
        api.getBootcamps()
    }
}