package com.aiglesiaspubill.androidavanzadofinal.ui.herolist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aiglesiaspubill.androidavanzadofinal.data.Repository
import com.aiglesiaspubill.androidavanzadofinal.data.RepositoryImpl
import com.aiglesiaspubill.androidavanzadofinal.utils.generateHeros
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

class HeroesListViewModelTest {

    //REGLAS
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    //SUT
    private lateinit var sut : HeroesListViewModel

    //Dependencias
    private lateinit var repository: Repository

    private val mainThreadSurrogate = newSingleThreadContext("UI Thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        repository = mockk()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }


    @Test
    fun `WHEN getHeroes EXPECTS success returns HeroListState`() = runTest {
        //GIVEN
        sut = HeroesListViewModel(repository)

        coEvery { repository.getHeroesWithCache() } returns HeroListState.Succes(generateHeros())

        //WHEN
        val actual = sut.getHeroes()
        val actualLiveData = sut.stateHeroes.getOrAwaitValue()

        //THEN
        Truth.assertThat(actualLiveData).isEqualTo(HeroListState.Succes(generateHeros()))
    }

}