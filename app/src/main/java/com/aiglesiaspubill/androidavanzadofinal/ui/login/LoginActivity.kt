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
        setListeners()
        observeLoginState()
    }

    private fun setListeners() {
        with(binding) {
            buttonLogin.setOnClickListener {
                val user: String = binding.editTextName.text.toString()
                val pass: String = binding.editTextPassword.text.toString()
                viewModel.login(user, pass)
            }
        }

    }

    private fun observeLoginState() {
        viewModel.stateLogin.observe(this) {
            when (it) {
                is LoginState.Failure -> Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                is LoginState.NetworkError -> Toast.makeText(
                    this,
                    "Error de otro tipo en el LOGIN",
                    Toast.LENGTH_SHORT
                ).show()
                is LoginState.Succes -> {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                }
                LoginState.loading -> Toast.makeText(this, "CARGANDO", Toast.LENGTH_SHORT).show()
            }
        }
    }
}