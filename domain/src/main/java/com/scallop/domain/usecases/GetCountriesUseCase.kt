package com.scallop.domain.usecases

import com.scallop.domain.common.BaseUseCase
import com.scallop.domain.common.Transformer
import com.scallop.domain.entities.CountryEntity
import com.scallop.domain.repositories.CovidRepository
import io.reactivex.Observable

class GetCountriesUseCase(
    transformer: Transformer<List<CountryEntity>>,
    private val repositories: CovidRepository
) : BaseUseCase<List<CountryEntity>>(transformer) {

    override fun createObservable(data: Map<String, Any>?): Observable<List<CountryEntity>> {
        return repositories.getInfoByCountry()
    }

    fun getInfoByCountry(): Observable<List<CountryEntity>> {
        val data = HashMap<String, Any>()
        return observable(data)
    }
}