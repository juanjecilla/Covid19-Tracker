package com.scallop.domain.repositories

import com.scallop.domain.entities.CountryEntity
import com.scallop.domain.entities.WorldwideEntity
import io.reactivex.Observable

interface CovidRepository {
    fun getWorldwideInfo(): Observable<WorldwideEntity>
    fun getInfoByCountry(): Observable<List<CountryEntity>>
}