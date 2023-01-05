package com.aiglesiaspubill.androidavanzadofinal.ui.herolist

import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.room.Room
import com.aiglesiaspubill.androidavanzadofinal.data.Repository
import com.aiglesiaspubill.androidavanzadofinal.data.RepositoryImpl
import com.aiglesiaspubill.androidavanzadofinal.data.local.HeroDatabase
import com.aiglesiaspubill.androidavanzadofinal.data.local.LocalDataSource
import com.aiglesiaspubill.androidavanzadofinal.data.local.LocalDataSourceImpl
import com.aiglesiaspubill.androidavanzadofinal.data.mappers.LocalToPresentationMapper
import com.aiglesiaspubill.androidavanzadofinal.data.mappers.RemoteToLocalMapper
import com.aiglesiaspubill.androidavanzadofinal.data.mappers.RemoteToPresentationMapper
import com.aiglesiaspubill.androidavanzadofinal.data.remote.DragonBallAPI
import com.aiglesiaspubill.androidavanzadofinal.data.remote.RemoteDataSourceImpl
import com.aiglesiaspubill.androidavanzadofinal.domain.Hero
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class HeroesListViewModel(private val repository : Repository): ViewModel() {

    private val _heros = MutableLiveData<List<Hero>>()
    val heros: LiveData<List<Hero>>
        get() = _heros

    //INICIAR CONEXION CON REPOSITORIO REMOTO
    companion object {
        const val TAG_TOKEN = "eyJraWQiOiJwcml2YXRlIiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJpZGVudGlmeSI6IkM3QTZBRENFLUM3MjUtNDlFRi04MEFDLTMxNDVCODkxQzg5NCIsImV4cGlyYXRpb24iOjY0MDkyMjExMjAwLCJlbWFpbCI6ImFpZ2xlc2lhc3B1YmlsbEBnbWFpbC5jb20ifQ.NjSKR-UPBTVSNIKunr8QPjwUiZJcnUObOv0pYG28Avc"
        private val TAG = "HeroesListViewModel"


    //AQUI ESTOY METIENDO TODAS LAS DEPENDECIAS
    val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(
            modelClass: Class<T>,
            extras: CreationExtras
        ): T {
            //INICIAR CONEXION CON REPOSITORIO LOCAL
            val application = checkNotNull(extras[APPLICATION_KEY])
            val db =
                Room.databaseBuilder(application, HeroDatabase::class.java, "database-name").build()
            val dao = db.getDAO()
            val localDataSource = LocalDataSourceImpl(dao)


            //INICIAR CONEXION CON REPOSITORIO REMOTO
            //Crear conexion con APIDRAGONBALL
            val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()

            //CREANDO INTERCEPTORES
            val httpLoggingInterceptor =
                HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }


            val okHttpClient = OkHttpClient.Builder()
                .authenticator { _, response ->
                    response.request.newBuilder()
                        .header("Authorization", "Bearer $TAG_TOKEN").build()
                }
                .addInterceptor(httpLoggingInterceptor)
                .build()

            //CREANDO RETROFIT
            var retrofit = Retrofit.Builder()
                .baseUrl("https://dragonball.keepcoding.education")
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()

            var api: DragonBallAPI = retrofit.create(DragonBallAPI::class.java)
            val remoteDataSource = RemoteDataSourceImpl(api)

            //CREO LOS MAPPERS
            val remoteToLocalMapper = RemoteToLocalMapper()
            val localToPresentationMapper = LocalToPresentationMapper()
            val remoteToPresentationMapper = RemoteToPresentationMapper()


            val repository = RepositoryImpl(
                localDataSource,
                remoteDataSource,
                remoteToPresentationMapper,
                remoteToLocalMapper,
                localToPresentationMapper
            )
            return HeroesListViewModel(repository) as T
        }
    }
    }


    //obtener Bootcamps
    fun getBootcamps() {
        viewModelScope.launch {
            val bootcamps = withContext(Dispatchers.IO){
                repository.getBootcamps()
            }
            Log.d(TAG, bootcamps.toString())
        }
    }

    //Obtener heroes
    fun getHeroes() {
        viewModelScope.launch {
            val heroes = withContext(Dispatchers.IO) {
                repository.getHeroesWithCache()
            }
            _heros.value = heroes
        }

    }

}