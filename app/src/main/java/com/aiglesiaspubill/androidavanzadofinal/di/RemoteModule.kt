package com.aiglesiaspubill.androidavanzadofinal.di

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.aiglesiaspubill.androidavanzadofinal.data.remote.DragonBallAPI
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    private val TAG_TOKEN =
        "eyJraWQiOiJwcml2YXRlIiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJpZGVudGlmeSI6IkM3QTZBRENFLUM3MjUtNDlFRi04MEFDLTMxNDVCODkxQzg5NCIsImV4cGlyYXRpb24iOjY0MDkyMjExMjAwLCJlbWFpbCI6ImFpZ2xlc2lhc3B1YmlsbEBnbWFpbC5jb20ifQ.NjSKR-UPBTVSNIKunr8QPjwUiZJcnUObOv0pYG28Avc"

    //NOS DA EL SHAREDPREFERENCES
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("NAME", Context.MODE_PRIVATE)
    }

    //NOS DA EL HTTPLOGINGINTERCEPTOR
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        return httpLoggingInterceptor
    }

    //NOS DA EL OKHTTPCLIENT
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        sharedPreferences: SharedPreferences
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                Log.d("AUTENTICADOR", "ENTRANDO EN EL INTERCEPTOR")
                val originalRequest = chain.request()
                val newRequest = originalRequest.newBuilder()
                    .header("Content-Type", "Application/Json")
                    .build()
                chain.proceed(newRequest)
            }
            .authenticator { _, response ->
                if (response.request.url.encodedPath.contains("api/auth/login")) {
                    response.request.newBuilder()
                        .header(
                            "Authorization",
                            "${sharedPreferences.getString("CREDENTIAL", null)}"
                        )
                        .build()
                } else {
                    response.request.newBuilder()
                        .header(
                            "Authorization",
                            "Bearer ${sharedPreferences.getString("TOKEN", null)}"
                        )
                        .build()
                }
            }
            .addInterceptor(httpLoggingInterceptor)
            .build()
        return okHttpClient
    }

    //NOS DA EL MOSHI
    @Provides
    fun provideMoshi(): Moshi {
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        return moshi
    }

    //NOS DA EL RETROFIT
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        var retrofit = Retrofit.Builder()
            .baseUrl("https://dragonball.keepcoding.education")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .build()
        return retrofit
    }

    //NOS DA EL API
    @Provides
    fun provideAPI(retrofit: Retrofit): DragonBallAPI {
        var api: DragonBallAPI = retrofit.create(DragonBallAPI::class.java)
        return api
    }
}