package com.aiglesiaspubill.androidavanzadofinal.ui.herolist

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.aiglesiaspubill.androidavanzadofinal.data.HeroListState
import com.aiglesiaspubill.androidavanzadofinal.data.Repository
import com.aiglesiaspubill.androidavanzadofinal.data.mappers.RemoteToPresentationMapper
import com.aiglesiaspubill.androidavanzadofinal.domain.Hero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HeroesListViewModel @Inject constructor(
    private val repository : Repository,
    private val remoteToPresentationMapper: RemoteToPresentationMapper
): ViewModel() {

    private val _heros = MutableLiveData<List<Hero>>()
    val heros: LiveData<List<Hero>>
        get() = _heros

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _state = MutableLiveData<HeroListState>()
    val state: LiveData<HeroListState>
        get() = _state

    //INICIAR CONEXION CON REPOSITORIO REMOTO
    companion object {
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

    //OBTENER HEROES
    fun getHeroesWithException() {
        viewModelScope.launch {
            val heroesListState = withContext(Dispatchers.IO) {
                repository.getHeroesWithException()
            }

            _state.value = heroesListState
        }
    }


}