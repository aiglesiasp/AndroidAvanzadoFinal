package com.aiglesiaspubill.androidavanzadofinal.ui.login

import android.content.Context
import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.aiglesiaspubill.androidavanzadofinal.data.Repository
import com.aiglesiaspubill.androidavanzadofinal.data.utils.generateHeros
import com.aiglesiaspubill.androidavanzadofinal.data.utils.generateToken
import com.aiglesiaspubill.androidavanzadofinal.data.utils.getOrAwaitValue
import com.aiglesiaspubill.androidavanzadofinal.ui.herolist.HeroListState
import com.aiglesiaspubill.androidavanzadofinal.ui.herolist.HeroesListViewModel
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {
    //REGLAS
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    //SUT
    private lateinit var sut : LoginViewModel

    //Dependencias
    private lateinit var repository: Repository
    private lateinit var sharedPreferences: SharedPreferences

    private val mainThreadSurrogate = newSingleThreadContext("UI Thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        repository = mockk()
        sharedPreferences = ApplicationProvider.getApplicationContext<Context>()
            .getSharedPreferences("SHARED PREFERENCES", Context.MODE_PRIVATE)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }


    @Test
    fun `WHEN getLogin EXPECTS success returns TOKEN`() = runTest {
        //GIVEN
        sut = LoginViewModel(repository, sharedPreferences)
        val user = "Aitor"
        val pass = "123456"

        coEvery { repository.getToken() } returns LoginState.Succes(generateToken())

        //WHEN
        val actual = sut.login(user, pass)
        val actualLiveData = sut.stateLogin.getOrAwaitValue()

        //THEN
        Truth.assertThat(actualLiveData).isEqualTo(LoginState.Succes(generateToken()))
    }

}