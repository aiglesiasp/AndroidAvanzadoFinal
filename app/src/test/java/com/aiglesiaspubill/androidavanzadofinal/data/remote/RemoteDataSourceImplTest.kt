package com.aiglesiaspubill.androidavanzadofinal.data.remote

import com.aiglesiaspubill.androidavanzadofinal.base.BaseNetworkTest
import com.aiglesiaspubill.androidavanzadofinal.ui.detail.DetailState
import com.aiglesiaspubill.androidavanzadofinal.utils.generateHero
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import javax.xml.transform.sax.TransformerHandler

@OptIn(ExperimentalCoroutinesApi::class)
class RemoteDataSourceImplTest : BaseNetworkTest() {

    private lateinit var remoteDataSourceImpl: RemoteDataSource

    @Before
    fun setUp() {
       remoteDataSourceImpl = RemoteDataSourceImpl(api)
    }

    @Test
    fun `WHEN getHeroesDetail EXPECT success and DetailState`() = runTest {
        //GIVEN
        val hero = generateHero()

        //WHEN
        val actual = remoteDataSourceImpl.getHeroDetail(hero.first().name)

        //THEN
        Truth.assertThat(actual).isNotNull()
        Truth.assertThat(actual).isEqualTo(DetailState.Succes(generateHero().first()))

    }

}