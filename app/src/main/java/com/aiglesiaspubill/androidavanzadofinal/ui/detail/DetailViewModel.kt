package com.aiglesiaspubill.androidavanzadofinal.ui.detail

import android.util.Log
import androidx.lifecycle.*
import com.aiglesiaspubill.androidavanzadofinal.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val _stateDetail = MutableLiveData<DetailState>()
    val stateDetail: LiveData<DetailState>
        get() = _stateDetail

    companion object {
        private val TAG = "DetailViewModel"
    }

    private fun setValueOnMainThread(value: DetailState) {
        viewModelScope.launch(Dispatchers.Main) {
            _stateDetail.value = value
        }
    }

    fun getHeroDetail(name: String) {
        viewModelScope.launch {
            val stateDetail = withContext(Dispatchers.IO) {
                repository.getHeroDetail(name)
            }
            when (stateDetail) {
                is DetailState.Failure -> Log.d("LOCATIONS", "Error al buscar localizaciones")
                is DetailState.NetworkError -> Log.d("LOCATIONS", "Error Network")
                is DetailState.Succes -> {
                    val locations = withContext(Dispatchers.IO) {
                        repository.getLocations(stateDetail.hero.id)

                    }
                    stateDetail.hero.locations = locations
                }

            }
            setValueOnMainThread(stateDetail)
        }
    }

    fun changeFavorite() {
        val stateDetail = stateDetail.value as DetailState.Succes
        val hero = stateDetail.hero
        hero.favorite = !hero.favorite
        viewModelScope.launch {
            repository.changeFavorite(hero.id)
        }
        setValueOnMainThread(stateDetail)
    }
}