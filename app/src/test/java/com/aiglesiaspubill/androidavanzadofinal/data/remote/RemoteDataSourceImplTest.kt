package com.aiglesiaspubill.androidavanzadofinal.data.remote

import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import javax.xml.transform.sax.TransformerHandler

@OptIn(ExperimentalCoroutinesApi::class)
class RemoteDataSourceImplTest {

    private lateinit var remoteDataSourceImpl: RemoteDataSource

    @Before
    fun setUp() {
       // remoteDataSourceImpl = RemoteDataSourceImpl()
    }

    @Test
    fun `WHEN getToken EXPECT success and return token`() = runTest {
        //GIVEN

        //WHEN
        val actual = remoteDataSourceImpl.getToken()

        //THEN
        Truth.assertThat(actual).isNotNull()

    }

}