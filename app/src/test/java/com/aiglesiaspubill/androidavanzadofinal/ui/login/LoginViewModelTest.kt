package com.aiglesiaspubill.androidavanzadofinal.ui.login

import android.content.Context
import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.aiglesiaspubill.androidavanzadofinal.data.Repository
import com.aiglesiaspubill.androidavanzadofinal.utils.generateToken
import com.aiglesiaspubill.androidavanzadofinal.utils.getOrAwaitValue
import com.aiglesiaspubill.androidavanzadofinal.utils.Shared
import com.google.common.truth.Truth
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)

class LoginViewModelTest {
    //REGLAS
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    //SUT
    private lateinit var sut: LoginViewModel

    //Dependencias
    private lateinit var repository: Repository
    private lateinit var sharedPreferences: SharedPreferences

    private val mainThreadSurrogate = newSingleThreadContext("UI Thread")

    //------------------------------------------------------------------------------------//
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        repository = mockk()
        sharedPreferences = ApplicationProvider.getApplicationContext<Context>()
            .getSharedPreferences(Shared.getSharedPreferencesName(), Context.MODE_PRIVATE)
        sut = LoginViewModel(repository, sharedPreferences)
    }

    //------------------------------------------------------------------------------------//
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    //------------------------------------------------------------------------------------//
    @Test
    fun `WHEN getLogin EXPECTS success returns TOKEN`() = runTest {
        //GIVEN
        val user = "aiglesiaspubill@gmail.com"
        val pass = "19871989aA"

        //WHEN
        val actual = sut.login(user, pass)
        val actualLiveData = sut.stateLogin.getOrAwaitValue()

        //THEN
        Truth.assertThat((actualLiveData as LoginState.Succes).token).isEqualTo(generateToken())
        //Truth.assertThat((actual as LoginState.Succes).token).isEqualTo("123456")
        //Truth.assertThat((actualLiveData as LoginState.Succes).token).isEqualTo("123456")
    }

}