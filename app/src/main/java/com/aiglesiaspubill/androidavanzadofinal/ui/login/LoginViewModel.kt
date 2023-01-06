package com.aiglesiaspubill.androidavanzadofinal.ui.login

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiglesiaspubill.androidavanzadofinal.data.LoginState
import com.aiglesiaspubill.androidavanzadofinal.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Credentials
import java.nio.charset.StandardCharsets
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: Repository, private val sharedPreferences: SharedPreferences) : ViewModel() {

    private val _stateLogin = MutableLiveData<LoginState>()
    val stateLogin: LiveData<LoginState>
        get() = _stateLogin

    private fun setValueOnMainThread(value: LoginState) {
        viewModelScope.launch(Dispatchers.Main) {
            _stateLogin.value = value
        }
    }

    //OBTENER CREDENCIALES
    private fun getCredentials(user: String, pass: String): String {
        return Credentials.basic(user, pass, StandardCharsets.UTF_8)
    }

    //HACER EL LOGIN
    fun login(user: String, pass: String) {
        setValueOnMainThread(LoginState.loading)

        //COMPRUEBO SI ESTA EN SHAREDPREFERENCES
        if(sharedPreferences.getString("TOKEN", null) == null) {
            sharedPreferences.edit().putString("CREDENTIAL", getCredentials(user,pass)).apply()
        }
        //LLAMO PARA OBTENER EL TOKEN
        viewModelScope.launch {
            val token = withContext(Dispatchers.IO) {
                repository.getToken()
            }
            setValueOnMainThread(token)
        }
    }
}