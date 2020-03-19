package com.scallop.data.repository

import com.scallop.data.api.CovidApi
import com.scallop.data.mappers.CovidDataEntityMapper
import com.scallop.domain.entities.CountryEntity
import com.scallop.domain.entities.WorldwideEntity
import io.reactivex.Observable

class CovidRemoteImpl constructor(private val api: CovidApi) : CovidDataSource {

    private val mMapper =
        CovidDataEntityMapper()

    override fun getWorldwideInfo(): Observable<WorldwideEntity> {
        return api.getWorldwideInfo().map { mMapper.mapToEntity(it) }
    }

    override fun getInfoByCountry(): Observable<List<CountryEntity>> {
        return api.getInfoByCountry().map {
            mMapper.mapToEntity(it)
        }
    }
}