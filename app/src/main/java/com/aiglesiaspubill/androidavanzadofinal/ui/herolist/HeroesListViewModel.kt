package com.aiglesiaspubill.androidavanzadofinal.ui.herolist

import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.room.Room
import com.aiglesiaspubill.androidavanzadofinal.data.Repository
import com.aiglesiaspubill.androidavanzadofinal.data.RepositoryImpl
import com.aiglesiaspubill.androidavanzadofinal.data.local.HeroDatabase
import com.aiglesiaspubill.androidavanzadofinal.data.local.LocalDataSourceImpl
import com.aiglesiaspubill.androidavanzadofinal.data.mappers.LocalToPresentationMapper
import com.aiglesiaspubill.androidavanzadofinal.data.mappers.RemoteToLocalMapper
import com.aiglesiaspubill.androidavanzadofinal.data.mappers.RemoteToPresentationMapper
import com.aiglesiaspubill.androidavanzadofinal.data.remote.DragonBallAPI
import com.aiglesiaspubill.androidavanzadofinal.data.remote.RemoteDataSourceImpl
import com.aiglesiaspubill.androidavanzadofinal.domain.Hero
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

@HiltViewModel
class HeroesListViewModel @Inject constructor(private val repository : RepositoryImpl): ViewModel() {

    private val _heros = MutableLiveData<List<Hero>>()
    val heros: LiveData<List<Hero>>
        get() = _heros

    //INICIAR CONEXION CON REPOSITORIO REMOTO
    companion object {
        const val TAG_TOKEN = "eyJraWQiOiJwcml2YXRlIiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJpZGVudGlmeSI6IkM3QTZBRENFLUM3MjUtNDlFRi04MEFDLTMxNDVCODkxQzg5NCIsImV4cGlyYXRpb24iOjY0MDkyMjExMjAwLCJlbWFpbCI6ImFpZ2xlc2lhc3B1YmlsbEBnbWFpbC5jb20ifQ.NjSKR-UPBTVSNIKunr8QPjwUiZJcnUObOv0pYG28Avc"
        private val TAG = "HeroesListViewModel"
    }

    //OBTENER BOOTCAMPS
    fun getBootcamps() {
        viewModelScope.launch {
            val bootcamps = withContext(Dispatchers.IO){
                repository.getBootcamps()
            }
            Log.d(TAG, bootcamps.toString())
        }
    }

    //OBTENER HEROES
    fun getHeroes() {
        viewModelScope.launch {
            val heroes = withContext(Dispatchers.IO) {
                repository.getHeroesWithCache()
            }
            _heros.value = heroes
        }
    }
}