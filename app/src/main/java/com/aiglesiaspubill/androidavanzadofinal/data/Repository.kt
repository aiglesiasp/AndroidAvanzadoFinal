package com.aiglesiaspubill.androidavanzadofinal.data

import android.content.Context
import com.aiglesiaspubill.androidavanzadofinal.data.local.LocalDataSource
import com.aiglesiaspubill.androidavanzadofinal.data.mappers.LocalToPresentationMapper
import com.aiglesiaspubill.androidavanzadofinal.data.mappers.RemoteToLocalMapper
import com.aiglesiaspubill.androidavanzadofinal.data.mappers.RemoteToPresentationMapper
import com.aiglesiaspubill.androidavanzadofinal.data.remote.RemoteDataSource
import com.aiglesiaspubill.androidavanzadofinal.domain.Bootcamp
import com.aiglesiaspubill.androidavanzadofinal.domain.Hero

class Repository {

    private val remoteDataSource = RemoteDataSource()
    private val localDataSource = LocalDataSource()
    private val remoteToPresentationMapper = RemoteToPresentationMapper()
    private val remoteToLocalMapper = RemoteToLocalMapper()
    private val localToPresentationMapper = LocalToPresentationMapper()

    //Obtener bootcamps
    suspend fun getBootcamps(): List<Bootcamp> {
        return remoteDataSource.getBootcamps()
    }

    //Obtener heroes
    suspend fun getHeroes(): List<Hero> {
        val remoteHero = remoteDataSource.getHeroes()
        return remoteToPresentationMapper.map(remoteHero)
    }

    //Obtener heroes
    suspend fun getHeroesWithCache(): List<Hero> {
        //1-Pido datos a local
        val localHeroList = localDataSource.getHeroes()
        //2-Compruebo si hay datos
        if(localHeroList.isEmpty()) {
            //3- Si no hay datos
            //3a-Pido datos en remoto
            val remoteHero = remoteDataSource.getHeroes()
            //3b-Guardo datos en local
            localDataSource.insertHeros(remoteToLocalMapper.map(remoteHero))
        }
        //4-Devuelvo datos local
        return localToPresentationMapper.map(localDataSource.getHeroes())


    }

    //FUNCION INICIAR LOCAL DATA BASE
    fun initLocalDataBase(context: Context) {
        localDataSource.initDatabase(context)
    }
}