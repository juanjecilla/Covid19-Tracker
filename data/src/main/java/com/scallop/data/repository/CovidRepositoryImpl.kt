package com.scallop.data.repository

import com.scallop.domain.entities.CountryEntity
import com.scallop.domain.entities.WorldwideEntity
import com.scallop.domain.repositories.CovidRepository
import io.reactivex.Observable


class CovidRepositoryImpl(
    private val remote: CovidRemoteImpl,
    private val cache: CovidCacheImpl
) : CovidRepository {


    override fun getWorldwideInfo(): Observable<WorldwideEntity> {
        return remote.getWorldwideInfo()
    }

    override fun getInfoByCountry(): Observable<List<CountryEntity>> {
        return remote.getInfoByCountry()
    }
}