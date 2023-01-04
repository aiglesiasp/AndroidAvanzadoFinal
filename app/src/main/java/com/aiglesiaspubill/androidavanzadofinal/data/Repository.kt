package com.aiglesiaspubill.androidavanzadofinal.data

import android.content.Context
import com.aiglesiaspubill.androidavanzadofinal.data.local.LocalDataSource
import com.aiglesiaspubill.androidavanzadofinal.data.mappers.RemoteToPresentationMapper
import com.aiglesiaspubill.androidavanzadofinal.data.remote.RemoteDataSource
import com.aiglesiaspubill.androidavanzadofinal.domain.Bootcamp
import com.aiglesiaspubill.androidavanzadofinal.domain.Hero

class Repository {

    private val remoteDataSource = RemoteDataSource()
    private val localDataSource = LocalDataSource()
    private val remoteToPresentationMapper = RemoteToPresentationMapper()

    //Obtener bootcamps
    suspend fun getBootcamps(): List<Bootcamp> {
        return remoteDataSource.getBootcamps()
    }

    //Obtener heroes
    suspend fun getHeroes(): List<Hero> {
        val remoteHero = remoteDataSource.getHeroes()
        return remoteToPresentationMapper.map(remoteHero)
    }

    //FUNCION INICIAR LOCAL DATA BASE
    fun initLocalDataBase(context: Context) {
        localDataSource.initDatabase(context)
    }
}