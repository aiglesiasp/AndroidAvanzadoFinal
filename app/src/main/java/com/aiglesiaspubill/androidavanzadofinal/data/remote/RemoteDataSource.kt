package com.aiglesiaspubill.androidavanzadofinal.data.remote

import com.aiglesiaspubill.androidavanzadofinal.data.remote.request.HerosRequest
import com.aiglesiaspubill.androidavanzadofinal.domain.Bootcamp
import com.aiglesiaspubill.androidavanzadofinal.domain.Hero
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class RemoteDataSource {

    companion object {
        const val TAG_TOKEN = "eyJraWQiOiJwcml2YXRlIiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJpZGVudGlmeSI6IkM3QTZBRENFLUM3MjUtNDlFRi04MEFDLTMxNDVCODkxQzg5NCIsImV4cGlyYXRpb24iOjY0MDkyMjExMjAwLCJlbWFpbCI6ImFpZ2xlc2lhc3B1YmlsbEBnbWFpbC5jb20ifQ.NjSKR-UPBTVSNIKunr8QPjwUiZJcnUObOv0pYG28Avc"
    }

    //Crear conexion con APIDRAGONBALL
    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    //CREANDO INTERCEPTORES
    private val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
        level = HttpLoggingInterceptor.Level.BODY
    }




    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val originalRequest = chain.request()
            val newRequest = originalRequest.newBuilder()
                .header("Authorization", "Bearer $TAG_TOKEN")
                .build()

            chain.proceed(newRequest)
        }
        .authenticator { _ , response ->
            response.request.newBuilder().header("Authorization", "Bearer $TAG_TOKEN").build()
        }
        .addInterceptor(httpLoggingInterceptor)
        .build()

    //CREANDO RETROFIT
    private var retrofit = Retrofit.Builder()
        .baseUrl("https://dragonball.keepcoding.education")
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private var api: DragonBallAPI = retrofit.create(DragonBallAPI::class.java)

    //FUNCION OBTENR BOOTCAMPS - LLAMADA A LA API
    suspend fun getBootcamps(): List<Bootcamp> {
        return api.getBootcamps()
    }

    //FUNCION OBTENR HEROES - LLAMADA A LA API
    suspend fun getHeroes(): List<Hero> {
        return api.getHeros(HerosRequest())
    }
}