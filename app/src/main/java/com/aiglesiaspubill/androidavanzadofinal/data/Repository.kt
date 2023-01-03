package com.aiglesiaspubill.androidavanzadofinal.data

import com.aiglesiaspubill.androidavanzadofinal.data.remote.RemoteDataSource
import com.aiglesiaspubill.androidavanzadofinal.domain.Bootcamp
import com.aiglesiaspubill.androidavanzadofinal.domain.Hero

class Repository {

    private val remoteDataSource = RemoteDataSource()

    //Obtener bootcamps
    suspend fun getBootcamps(): List<Bootcamp> {
        return remoteDataSource.getBootcamps()
    }

    //Obtener heroes
    suspend fun getHeroes(): List<Hero> {
        return remoteDataSource.getHeroes()
    }
}