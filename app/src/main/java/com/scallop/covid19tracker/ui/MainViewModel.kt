package com.scallop.covid19tracker.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.scallop.covid19tracker.common.BaseViewModel
import com.scallop.covid19tracker.model.Country
import com.scallop.covid19tracker.model.Data
import com.scallop.covid19tracker.model.Error
import com.scallop.covid19tracker.model.Status
import com.scallop.covid19tracker.model.Worldwide
import com.scallop.domain.common.Mapper
import com.scallop.domain.entities.CountryEntity
import com.scallop.domain.entities.WorldwideEntity
import com.scallop.domain.usecases.GetCountriesUseCase
import com.scallop.domain.usecases.GetWoldwideUseCase

class MainViewModel(
    private val mGetWoldwideUseCase: GetWoldwideUseCase,
    private val mGetCountriesUseCase: GetCountriesUseCase,
    private val mMapperWorldwide: Mapper<WorldwideEntity, Worldwide>,
    private val mMapperCountries: Mapper<List<CountryEntity>, List<Country>>
) : BaseViewModel() {

    companion object {
        private const val TAG = "MovieListViewModel"
    }

    private var mWorldwideInfo = MutableLiveData<Data<Worldwide>>()
    var mCountriesInfo = MutableLiveData<Data<List<Country>>>()

    fun fetchWorldwideInfo() {
        val disposable = mGetWoldwideUseCase.getWorldwideInfo()
            .flatMap { mMapperWorldwide.observable(it) }
            .subscribe({ response ->
                Log.d(TAG, "On Next Called")
                mWorldwideInfo.value = Data(responseType = Status.SUCCESSFUL, data = response)
            }, { error ->
                Log.d(TAG, "On Error Called " + error.message)
                mWorldwideInfo.value = Data(responseType = Status.ERROR, error = Error(error.message))
            }, {
                Log.d(TAG, "On Complete Called")
            })

        addDisposable(disposable)
    }

    fun fetchInfoByCountry() {
        val disposable = mGetCountriesUseCase.getInfoByCountry()
            .flatMap { mMapperCountries.observable(it) }
            .subscribe({ response ->
                Log.d(TAG, "On Next Called")
                mCountriesInfo.value = Data(responseType = Status.SUCCESSFUL, data = response)
            }, { error ->
                Log.d(TAG, "On Error Called " + error.message)
                mCountriesInfo.value = Data(responseType = Status.ERROR, error = Error(error.message))
            }, {
                Log.d(TAG, "On Complete Called")
            })
        addDisposable(disposable)
    }

    fun getWorldwideInfoLiveData() = mWorldwideInfo
    fun getCountriesInfoLiveData() = mCountriesInfo
}