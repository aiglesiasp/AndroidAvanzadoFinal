package com.aiglesiaspubill.androidavanzadofinal.data

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import com.aiglesiaspubill.androidavanzadofinal.fakes.FakeRemoteDataSource
import com.aiglesiaspubill.androidavanzadofinal.data.local.LocalDataSource
import com.aiglesiaspubill.androidavanzadofinal.data.mappers.Mappers
import com.aiglesiaspubill.androidavanzadofinal.data.remote.RemoteDataSource
import com.aiglesiaspubill.androidavanzadofinal.domain.Location
import com.aiglesiaspubill.androidavanzadofinal.fakes.FakeLocalDataSource
import com.aiglesiaspubill.androidavanzadofinal.ui.detail.DetailState
import com.aiglesiaspubill.androidavanzadofinal.ui.herolist.HeroListState
import com.aiglesiaspubill.androidavanzadofinal.ui.login.LoginState
import com.aiglesiaspubill.androidavanzadofinal.utils.Shared
import com.aiglesiaspubill.androidavanzadofinal.utils.generateHero
import com.aiglesiaspubill.androidavanzadofinal.utils.generateLocations
import com.google.common.truth.Truth
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import retrofit2.HttpException
import retrofit2.Response

@RunWith(RobolectricTestRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class RepositoryImplTest {

    //UUT o SUT
    private lateinit var sut: RepositoryImpl
    private lateinit var fakeRemoteDataSource: RemoteDataSource
    private lateinit var fakeLocalDataSource: LocalDataSource
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var mappers: Mappers
    //------------------------------------------------------------------------------------//
    @Before
    fun setUp() {
        fakeLocalDataSource = FakeLocalDataSource()
        fakeRemoteDataSource = FakeRemoteDataSource()
        sharedPreferences = ApplicationProvider.getApplicationContext<Context>()
            .getSharedPreferences(Shared.getSharedPreferencesName(), Context.MODE_PRIVATE)
        mappers = Mappers()
        sut = RepositoryImpl(fakeLocalDataSource, fakeRemoteDataSource, Mappers(), sharedPreferences)
    }

    //------------------------------------------------------------------------------------//
    @Test
    fun `WHEN getLocations EXPECT SUCCESS not empty list` () = runTest {
        //GIVEN --> lo que necesito
        // WHEN
        val actual = sut.getLocations("SUCCESS")
        //VERSION TRUTH
        Truth.assertThat(actual).isNotEmpty()
        Truth.assertThat(actual.first().longitud).isNotNull()
        Truth.assertThat(actual.first().id).isEqualTo("AB3A873C-37B4-4FDE-A50F-8014D40D94FE")
        Truth.assertThat(actual).containsExactlyElementsIn(generateLocations())
    }

    //------------------------------------------------------------------------------------//
    @Test
    fun `WHEN getLocations EXPECT SUCCESS return emptyList`() = runTest {
        //GIVEN
        //WHEN
        val actual = sut.getLocations("SUCCESS_BUT_NULL")
        //THEN
        Truth.assertThat(actual).isEmpty()
    }


    //------------------------------------------------------------------------------------//
    @Test
    fun `WHEN getHerosWithCache EXPECT SUCCESS return HeroListState from local and remote called`() = runTest {
        //GIVEN
        //WHEN
        val actual = sut.getHeroesWithCache()
        //THEN
        assert(actual is HeroListState.Succes)
        Truth.assertThat((actual as HeroListState.Succes).heros.get(0).name).isEqualTo("Maestro Roshi")
    }

    //------------------------------------------------------------------------------------//
    @Test
    fun `WHEN getHerosDetail EXPECT SUCCESS return DetailState`() = runTest {
        //GIVEN
        //WHEN
        val actual = sut.getHeroDetail("SUCCESS")
        //THEN
        assert(actual is DetailState.Succes)
        Truth.assertThat((actual as DetailState.Succes).hero.name).isEqualTo("Maestro Roshi")
    }

    //------------------------------------------------------------------------------------//
    @Test
    fun `WHEN getHerosDetail EXPECT FAILURE return NULL`() = runTest {
        //GIVEN
        //WHEN
        val actual = sut.getHeroDetail("NULL")
        //THEN
        assert(actual is DetailState.Failure)
        Truth.assertThat((actual as DetailState.Failure).error).isEqualTo("Null pointer exception")
    }

    //------------------------------------------------------------------------------------//
    @Test
    fun `WHEN getHerosDetail EXPECT FAILURE return NETWORK ERROR`() = runTest {
        //GIVEN
        //WHEN
        val actual = sut.getHeroDetail("NETWORK_ERROR")
        //THEN
        assert(actual is DetailState.NetworkError)
        assert((actual as DetailState.NetworkError).code == 204)
    }

    //------------------------------------------------------------------------------------//
    @Test
    fun `WHEN getHerosDetail EXPECT SUCCES return NULL`() = runTest {
        //GIVEN
        //WHEN
        val actual = sut.getHeroDetail("SUCCESS_BUT_NULL")
        //THEN
        assert(actual is DetailState.Failure)
        Truth.assertThat((actual as DetailState.Failure).error).isEqualTo(null)
    }

    //------------------------------------------------------------------------------------//
    @Test
    fun `WHEN getToken EXPECT SUCCESS return LoginState`() = runTest {
        //GIVEN
        //WHEN
        val token = sut.getToken()
        //THEN
        Truth.assertThat(token).isNotNull()
        assert(token is LoginState.Succes)
        Truth.assertThat((token as LoginState.Succes).token).isEqualTo("123456")
    }
}