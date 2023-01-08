package com.aiglesiaspubill.androidavanzadofinal.ui.login

import android.content.SharedPreferences
import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiglesiaspubill.androidavanzadofinal.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Credentials
import java.nio.charset.StandardCharsets
import java.util.regex.Pattern
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

    //CHEKEAR USUARIO
    private fun checkUser(user: String): Boolean {
        if (user.isEmpty() || user.isBlank()) return false
        if(!PatternsCompat.EMAIL_ADDRESS.matcher(user).matches()) return false
        return true
    }

    //CHEKEAR PASSWORD
    private fun checkPassword(password: String): Boolean {
        val passwordRegex = Pattern.compile(
            "^" +
                    "(?=.*[0-9])" +   //por lo menos 1 digito
                    "(?=.*[a-z])" +     //1 letra minuscula
                    "(?=.*[A-Z])" +     //1 letra mayuscula
                    "(?=\\S+$)" +     //no espacios en blanco
                    ".{4,}" +     //por lo menos 4 caracteres
                    "$"
        )
        if (password.isEmpty() || password.isBlank()) return false
        if(!passwordRegex.matcher(password).matches()) return false
        return true
    }



    //HACER EL LOGIN
    fun login(user: String, pass: String) {
        setValueOnMainThread(LoginState.loading)

        //COMPROBAR LOGIN y USUARIO
        val checkUser = checkUser(user)
        val checkPass = checkPassword(pass)
        if (!checkUser) {
            setValueOnMainThread(LoginState.Failure("Error en Usuario"))
            return
        }
        if(!checkPass){
            setValueOnMainThread(LoginState.Failure("Error en Contrasenya"))
            return
        }

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