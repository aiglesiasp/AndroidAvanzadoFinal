package com.aiglesiaspubill.androidavanzadofinal.ui.herolist

import androidx.lifecycle.*
import com.aiglesiaspubill.androidavanzadofinal.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HeroesListViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val _stateHeroes = MutableLiveData<HeroListState>()
    val stateHeroes: LiveData<HeroListState>
        get() = _stateHeroes

    companion object {
        private val TAG = "HeroesListViewModel"
    }

    private fun setValueOnMainThread(value: HeroListState) {
        viewModelScope.launch(Dispatchers.Main) {
            _stateHeroes.value = value
        }
    }

    fun getHeroes() {
        viewModelScope.launch {
            val heroesListState = withContext(Dispatchers.IO) {
                repository.getHeroesWithCache()
            }
            setValueOnMainThread(heroesListState)
        }
    }
}