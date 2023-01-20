package com.aiglesiaspubill.androidavanzadofinal.data

import android.content.SharedPreferences
import com.aiglesiaspubill.androidavanzadofinal.data.local.LocalDataSource
import com.aiglesiaspubill.androidavanzadofinal.data.mappers.Mappers
import com.aiglesiaspubill.androidavanzadofinal.data.remote.RemoteDataSource
import com.aiglesiaspubill.androidavanzadofinal.domain.Location
import com.aiglesiaspubill.androidavanzadofinal.ui.detail.DetailState
import com.aiglesiaspubill.androidavanzadofinal.ui.herolist.HeroListState
import com.aiglesiaspubill.androidavanzadofinal.ui.login.LoginState
import retrofit2.HttpException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val mappers: Mappers,
    private val sharedPreferences: SharedPreferences
) : Repository {

    companion object {
        val TOKEN = "TOKEN"
    }

    //FUNCION PARA PASAR DE UN RESULTADO A OTRO
    override suspend fun getHeroes(): HeroListState {
        val remoteResult = remoteDataSource.getHeros()
        remoteResult.onSuccess {
            return HeroListState.Succes(mappers.mapRemoteToPresentation(remoteResult.getOrThrow()))
        }
        return HeroListState.Failure("Error al obtener heroes")
    }

    //OBTENER HEROES CON EXCEPCIONES
    override suspend fun getHeroesWithCache(): HeroListState {
        var localResult = localDataSource.getHeroes()
        val remoteResult = getHeroes()
        if (localResult.isEmpty()) {
            when (remoteResult) {
                is HeroListState.Failure -> return remoteResult
                is HeroListState.NetworkError -> return remoteResult
                is HeroListState.Succes -> {
                    localResult = mappers.mapPresentationToLocal(remoteResult.heros)
                    localDataSource.insertAll(localResult)
                }
            }
        }
        return remoteResult
    }

    //OBTENER EL TOKEN
    override suspend fun getToken(): LoginState {
        val token = remoteDataSource.getToken()
        return when {
            token.isSuccess -> {
                sharedPreferences.edit().putString(TOKEN, token.getOrThrow()).apply()
                return LoginState.Succes(token.getOrThrow())
            }
            token.isFailure -> LoginState.Failure("Error al intentar conseguir el token")
            else -> {
                LoginState.NetworkError(0)
            }
        }
    }

    //OBTENER EL DETALLE DEL HEROE
    override suspend fun getHeroDetail(name: String): DetailState {
        val result = remoteDataSource.getHeroDetail(name)
        return when {
            result.isSuccess ->
                result.getOrNull()?.let {
                    DetailState.Succes(mappers.mapRemoteToPresentationOneHero(it))
                }
                    ?: DetailState.Failure(result.exceptionOrNull()?.message)
            else -> {
                when (val exception = result.exceptionOrNull()) {
                    is HttpException -> DetailState.NetworkError(exception.code())
                    else -> {
                        DetailState.Failure(result.exceptionOrNull()?.message)
                    }
                }
            }
        }
    }

    //OBTENER LOCALIZACIONES
    override suspend fun getLocations(heroId: String): List<Location> {
        val locations = remoteDataSource.getLocations(heroId)
        locations.onSuccess {
            //Devolver un mapper con las localizaciones
            return mappers.mapRemoteLocationsToLocations(locations.getOrThrow())
        }
        return emptyList()
    }

    //OBTENER EL FAVORITO
    override suspend fun changeFavorite(id: String) {
        remoteDataSource.changeFavorite(id)
    }


}