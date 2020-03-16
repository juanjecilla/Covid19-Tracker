package com.scallop.covid19tracker.di

import androidx.room.Room
import com.scallop.covid19tracker.common.AsyncObservableTransformer
import com.scallop.covid19tracker.mappers.CountryEntityMapper
import com.scallop.covid19tracker.mappers.WorldwideEntityMapper
import com.scallop.covid19tracker.ui.MainViewModel
import com.scallop.data.api.CovidApi
import com.scallop.data.db.CovidDatabase
import com.scallop.data.mappers.CovidDataEntityMapper
import com.scallop.data.mappers.CovidEntityDataMapper
import com.scallop.data.repository.CovidCacheImpl
import com.scallop.data.repository.CovidRemoteImpl
import com.scallop.data.repository.CovidRepositoryImpl
import com.scallop.domain.usecases.GetCountriesUseCase
import com.scallop.domain.usecases.GetWoldwideUseCase
import com.scallop.domain.repositories.CovidRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit

val mRepositoryModules = module {
    single(name = "remote") { CovidRemoteImpl(api = get(API)) }
    single(name = "local") {
        CovidCacheImpl(
            database = get(DATABASE), entityToDataMapper = CovidEntityDataMapper(),
            dataToEntityMapper = CovidDataEntityMapper()
        )
    }
    single { CovidRepositoryImpl(remote = get("remote"), cache = get("local")) as CovidRepository }
}

val mUseCaseModules = module {
    factory(name = GET_COUNTRIES_USECASE) {
        GetCountriesUseCase(
            transformer = AsyncObservableTransformer(),
            repositories = get()
        )
    }
    factory(name = GET_WORLDWIDE_USECASE) {
        GetWoldwideUseCase(
            transformer = AsyncObservableTransformer(),
            repositories = get()
        )
    }
}

val mNetworkModules = module {
    single(name = RETROFIT_INSTANCE) { createNetworkClient(BASE_URL) }
    single(name = API) { (get(RETROFIT_INSTANCE) as Retrofit).create(CovidApi::class.java) }
}

val mLocalModules = module {
    single(name = DATABASE) {
        Room.databaseBuilder(
            androidApplication(),
            CovidDatabase::class.java,
            "covid_database"
        ).build()
    }
}

val mViewModels = module {
    viewModel {
        MainViewModel(
            mGetWoldwideUseCase = get(GET_WORLDWIDE_USECASE),
            mGetCountriesUseCase = get(GET_COUNTRIES_USECASE),
            mMapperWorldwide = WorldwideEntityMapper(),
            mMapperCountries = CountryEntityMapper()
        )
    }
}

private const val BASE_URL = "https://coronavirus-19-api.herokuapp.com/"
private const val RETROFIT_INSTANCE = "Retrofit"
private const val API = "Api"
private const val GET_WORLDWIDE_USECASE = "getWorldwideUseCase"
private const val GET_COUNTRIES_USECASE = "getCountriesUseCase"
private const val REMOTE = "remote response"
private const val DATABASE = "database"