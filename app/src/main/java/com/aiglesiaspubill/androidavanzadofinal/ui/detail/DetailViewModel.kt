package com.aiglesiaspubill.androidavanzadofinal.ui.detail

import androidx.lifecycle.*
import com.aiglesiaspubill.androidavanzadofinal.ui.herolist.HeroListState
import com.aiglesiaspubill.androidavanzadofinal.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository : Repository,
): ViewModel() {

    private val _stateDetail = MutableLiveData<DetailState>()
    val stateDetail: LiveData<DetailState>
        get() = _stateDetail

    //INICIAR CONEXION CON REPOSITORIO REMOTO
    companion object {
        private val TAG = "DetailViewModel"
    }

    private fun setValueOnMainThread(value: DetailState) {
        viewModelScope.launch(Dispatchers.Main) {
            _stateDetail.value = value
        }
    }

    //OBTENER HEROES
    fun getHeroDetail(name: String) {
        viewModelScope.launch {
            val stateDetail = withContext(Dispatchers.IO) {
                repository.getHeroDetail(name)
            }
            setValueOnMainThread(stateDetail)
        }
    }


}