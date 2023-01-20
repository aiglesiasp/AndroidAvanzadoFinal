package com.aiglesiaspubill.androidavanzadofinal.data.remote

import com.aiglesiaspubill.androidavanzadofinal.base.BaseNetworkTest
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

@OptIn(ExperimentalCoroutinesApi::class)
class RemoteDataSourceImplTest : BaseNetworkTest() {

    private lateinit var sut: RemoteDataSource

    //------------------------------------------------------------------------------------//
    @Before
    fun setUp() {
        sut = RemoteDataSourceImpl(api)
    }

    //------------------------------------------------------------------------------------//
    @Test
    fun `WHEN getHeroes EXPECT success list of HerosRemote`() = runTest {
        //GIVEN
        //WHEN
        val actual = sut.getHeros()
        //THEN
        Truth.assertThat(actual).isNotNull()
        Truth.assertThat(actual.getOrNull()?.get(0)?.name).isEqualTo("Maestro Roshi")

    }

    //------------------------------------------------------------------------------------//
    @Test
    fun `WHEN getHeroesDetail EXPECT success HeroRemote`() = runTest {
        //GIVEN
        //WHEN
        val actual = sut.getHeroDetail(anyString())
        //THEN
        Truth.assertThat(actual).isNotNull()
        Truth.assertThat(actual.getOrNull()?.name).isEqualTo("Maestro Roshi")

    }

    //------------------------------------------------------------------------------------//
    @Test
    fun `WHEN getToken EXPECT success String`() = runTest {
        //GIVEN
        //WHEN
        val actual = sut.getToken()
        //THEN
        Truth.assertThat(actual).isNotNull()
        Truth.assertThat(actual.getOrNull().toString()).isEqualTo("123456")
    }

    //------------------------------------------------------------------------------------//
    @Test
    fun `WHEN getLocation EXPECT success Location`() = runTest {
        //GIVEN
        //WHEN
        val actual = sut.getLocations("D13A40E5-4418-4223-9CE6-D2F9A28EBE94")
        //THEN
        Truth.assertThat(actual).isNotNull()
        Truth.assertThat(actual.getOrNull()?.get(0)?.latitud).isEqualTo("35.71867899343361")
    }

}