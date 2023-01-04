package com.aiglesiaspubill.androidavanzadofinal.ui.herolist

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiglesiaspubill.androidavanzadofinal.data.Repository
import com.aiglesiaspubill.androidavanzadofinal.domain.Hero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HeroesListViewModel: ViewModel() {

    private val repository = Repository()

    private val _heros = MutableLiveData<List<Hero>>()
    val heros: LiveData<List<Hero>>
        get() = _heros

    companion object {
        private val TAG = "HeroesListViewModel"
    }

    //FUNCION INICIAR DATABASE
    fun initDatabase(context: Context) {
        repository.initLocalDataBase(context)
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