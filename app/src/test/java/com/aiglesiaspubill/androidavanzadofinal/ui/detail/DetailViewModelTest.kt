package com.aiglesiaspubill.androidavanzadofinal.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aiglesiaspubill.androidavanzadofinal.data.Repository
import com.aiglesiaspubill.androidavanzadofinal.data.remote.response.LocationRemote
import com.aiglesiaspubill.androidavanzadofinal.utils.generateHero
import com.aiglesiaspubill.androidavanzadofinal.utils.generateLocations
import com.aiglesiaspubill.androidavanzadofinal.utils.getOrAwaitValue
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

class DetailViewModelTest {
    //REGLAS
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    //SUT
    private lateinit var sut : DetailViewModel

    //Dependencias
    private lateinit var repository: Repository

    private val mainThreadSurrogate = newSingleThreadContext("UI Thread")

    //------------------------------------------------------------------------------------//
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        repository = mockk()
    }

    //------------------------------------------------------------------------------------//
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }


    //------------------------------------------------------------------------------------//
    @Test
    fun `WHEN getHeroDetail EXPECTS success returns DetailListState`() = runTest {
        //GIVEN
        sut = DetailViewModel(repository)
        val hero = generateHero()

        coEvery { repository.getHeroDetail(hero.first().name) } returns DetailState.Succes(generateHero().first())
        coEvery { repository.getLocations(hero.first().id) } returns generateLocations()

        //WHEN
        val actual = sut.getHeroDetail(hero.first().name)
        val actualLiveData = sut.stateDetail.getOrAwaitValue()

        //THEN
        Truth.assertThat(actualLiveData).isEqualTo(DetailState.Succes(generateHero().first()))
    }

}