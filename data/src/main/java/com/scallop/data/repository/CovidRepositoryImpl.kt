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
        val remoteResults = remote.getWorldwideInfo()

        return cache.getWorldwideInfo().mergeWith(remoteResults.doOnNext {
            cache.setWorldwideInfo(it)
        })
    }

    override fun getInfoByCountry(): Observable<List<CountryEntity>> {
        val remoteResults = remote.getInfoByCountry()

        return cache.getInfoByCountry().mergeWith(remoteResults.doOnNext {
            if (it.isNotEmpty()) {
                cache.setInfoByCountry(it)
            }
        })
    }
}