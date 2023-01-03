package com.aiglesiaspubill.androidavanzadofinal.ui.herolist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiglesiaspubill.androidavanzadofinal.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HeroesListViewModel: ViewModel() {

    private val repository = Repository()

    companion object {
        private val TAG = "HeroesListViewModel"
    }

    fun getBootcamps() {
        viewModelScope.launch {
            val bootcamps = withContext(Dispatchers.IO){
                repository.getBootcamps()
            }
            Log.d(TAG, bootcamps.toString())
        }
    }

}