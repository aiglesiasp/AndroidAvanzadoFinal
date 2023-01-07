package com.aiglesiaspubill.androidavanzadofinal.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.aiglesiaspubill.androidavanzadofinal.databinding.ActivityLoginBinding
import com.aiglesiaspubill.androidavanzadofinal.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //FUNCIONES DE ESCUCHA
        setListeners()
        //FUNCIONES DE OBSERVADORES
        observeLoginState()
    }


    //FUNCION DE ESCUCHAS
    private fun setListeners() {
        with(binding) {
            buttonLogin.setOnClickListener {
                val user: String = binding.editTextName.text.toString()
                val pass: String = binding.editTextPassword.text.toString()
                //LLAMAREMOS AL VIEWMODEL PARA QUE HAGA EL LOGIN
                if(user.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(this@LoginActivity,"ERROR EN USUARIO y/o PASSWORD",Toast.LENGTH_LONG).show()
                } else {
                    viewModel.login(user, pass)
                }
            }
        }

    }

    //FUNCION DE OBSERVADORES
    private fun observeLoginState() {
        viewModel.stateLogin.observe(this) {
            when(it) {
                is LoginState.Failure -> Toast.makeText(this, "Error en el LOGIN", Toast.LENGTH_LONG).show()
                is LoginState.NetworkError -> Toast.makeText(this, "Error de otro tipo en el LOGIN", Toast.LENGTH_LONG).show()
                is LoginState.Succes -> {
                    Toast.makeText(this, "LOGIN COMPLETADO CORRECTAMENTE", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                }
                LoginState.loading -> Toast.makeText(this, "CARGANDO", Toast.LENGTH_SHORT).show()
            }
        }
    }
}