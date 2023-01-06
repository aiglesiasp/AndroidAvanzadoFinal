package com.aiglesiaspubill.androidavanzadofinal.data

import com.aiglesiaspubill.androidavanzadofinal.data.local.LocalDataSource
import com.aiglesiaspubill.androidavanzadofinal.data.mappers.LocalToPresentationMapper
import com.aiglesiaspubill.androidavanzadofinal.data.mappers.RemoteToLocalMapper
import com.aiglesiaspubill.androidavanzadofinal.data.mappers.RemoteToPresentationMapper
import com.aiglesiaspubill.androidavanzadofinal.data.remote.RemoteDataSource
import com.aiglesiaspubill.androidavanzadofinal.domain.Bootcamp
import com.aiglesiaspubill.androidavanzadofinal.domain.Hero
import retrofit2.HttpException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource,
                                         private val remoteDataSource: RemoteDataSource,
                                         private val remoteToPresentationMapper: RemoteToPresentationMapper,
                                         private val remoteToLocalMapper: RemoteToLocalMapper,
                                         private val localToPresentationMapper: LocalToPresentationMapper

                 ): Repository {

    //1-LOGIN
    override suspend fun getLogin(): String {
        // Hay que hacer el LOGIN
        return ""
    }
    //2-BOOTCAMPS
    override suspend fun getBootcamps(): List<Bootcamp> {
        return remoteDataSource.getBootcamps()
    }

    //3-OBTENER HEROES -- NO LO NECESITARE-- SE PODRA BORRAR
    override suspend fun getHeroes(): List<Hero> {
        val remoteHero = remoteDataSource.getHeroes()
        return remoteToPresentationMapper.map(remoteHero)
    }

    //4-OBTENER HEROES MIRANDO LOCAL
    override suspend fun getHeroesWithCache(): List<Hero> {
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

    override suspend fun getHeroesWithException(): HeroListState {
        val result = remoteDataSource.getHerosWithException()
        return when {
            result.isSuccess -> HeroListState.Succes(remoteToPresentationMapper.map(result.getOrThrow()))
            else -> {
                val exception = result.exceptionOrNull()
                when (exception) {
                    is HttpException -> HeroListState.NetworkError(exception.code())
                    else -> {
                        HeroListState.Failure(result.exceptionOrNull()?.message)
                    }
                }
            }
        }
    }
}