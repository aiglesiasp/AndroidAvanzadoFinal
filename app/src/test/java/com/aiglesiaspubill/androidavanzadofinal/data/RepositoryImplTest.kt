package com.aiglesiaspubill.androidavanzadofinal.data

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import com.aiglesiaspubill.androidavanzadofinal.data.fakes.FakeLocalDataSource
import com.aiglesiaspubill.androidavanzadofinal.data.fakes.FakeRemoteDataSource
import com.aiglesiaspubill.androidavanzadofinal.data.local.LocalDataSource
import com.aiglesiaspubill.androidavanzadofinal.data.mappers.Mappers
import com.aiglesiaspubill.androidavanzadofinal.data.remote.RemoteDataSource
import com.aiglesiaspubill.androidavanzadofinal.data.remote.request.LocationRequest
import com.aiglesiaspubill.androidavanzadofinal.data.utils.generateHero
import com.aiglesiaspubill.androidavanzadofinal.data.utils.generateHerosLocal
import com.aiglesiaspubill.androidavanzadofinal.data.utils.generateRemoteLocations
import com.aiglesiaspubill.androidavanzadofinal.ui.herolist.HeroListState
import com.google.common.truth.Truth
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import kotlin.reflect.jvm.internal.impl.load.java.JavaClassesTracker.Default

@OptIn(ExperimentalCoroutinesApi::class)
class RepositoryImplTest {

    //UUT o SUT
    private lateinit var repositoryImpl: RepositoryImpl
    private lateinit var fakeRemoteDataSource: RemoteDataSource
    private lateinit var localDataSource: LocalDataSource
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var mappers: Mappers

    @Before
    fun setUp() {
        localDataSource = mockk()
        fakeRemoteDataSource = FakeRemoteDataSource(generateHero().first())
        sharedPreferences = ApplicationProvider.getApplicationContext<Context>()
            .getSharedPreferences("SHARED PREFERENCES", Context.MODE_PRIVATE)
        mappers = Mappers()

        repositoryImpl = RepositoryImpl(localDataSource, fakeRemoteDataSource, Mappers(), sharedPreferences)
    }

    //TESTING PARA FUNCION GETLOCATIONS
    @Test
    fun `WHEN getLocations EXPECT SUCCESS not empty list` () = runTest {
        //GIVEN --> lo que necesito
        val locationRequest = LocationRequest().id

        // WHEN
        val actual = repositoryImpl.getLocations(locationRequest)

        //THEN
        //VERSION JUNIT
        assert(actual.isNotEmpty())

        //VERSION TRUTH
        Truth.assertThat(actual).isNotEmpty()
        Truth.assertThat(actual.first().longitud).isNotNull()
        Truth.assertThat(actual).containsExactlyElementsIn(generateRemoteLocations())
    }

    @Test
    fun `WHEN getHerosWithCache THEN SUCCESS return list from local and remote called`() = runTest {
        //GIVEN
        coEvery { localDataSource.getHeroes() } returns generateHerosLocal()
        coEvery { localDataSource.insertHeros(any()) } returns Unit

        //WHEN
        val actual = repositoryImpl.getHeroesWithCache()

        //THEN
        Truth.assertThat(actual).isEqualTo(HeroListState.Succes(Mappers().mapLocalToPresentation(generateHerosLocal())))
    }

}