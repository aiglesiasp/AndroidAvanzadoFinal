package com.aiglesiaspubill.androidavanzadofinal.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.aiglesiaspubill.androidavanzadofinal.data.local.HeroDAO
import com.aiglesiaspubill.androidavanzadofinal.data.local.HeroDatabase
import com.aiglesiaspubill.androidavanzadofinal.data.remote.DragonBallAPI
import com.aiglesiaspubill.androidavanzadofinal.ui.herolist.HeroesListViewModel
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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

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
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .authenticator { _, response ->
                response.request.newBuilder()
                    .header("Authorization", "Bearer ${HeroesListViewModel.TAG_TOKEN}").build()
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
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        return retrofit
    }

    //NOS DA EL API
    @Provides
    fun provideAPI(retrofit: Retrofit) : DragonBallAPI {
        var api: DragonBallAPI = retrofit.create(DragonBallAPI::class.java)
        return api
    }
}