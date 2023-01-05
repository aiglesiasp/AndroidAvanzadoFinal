package com.aiglesiaspubill.androidavanzadofinal.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.aiglesiaspubill.androidavanzadofinal.data.local.HeroDAO
import com.aiglesiaspubill.androidavanzadofinal.data.local.HeroDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    //NOS DA EL DATABASE
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): HeroDatabase {
        return Room.databaseBuilder(context, HeroDatabase::class.java, "database-name").build()
    }

    //NOS DA EL DAO
    @Provides
    fun provideDao(database: HeroDatabase) : HeroDAO {
        return database.getDAO()
    }
}