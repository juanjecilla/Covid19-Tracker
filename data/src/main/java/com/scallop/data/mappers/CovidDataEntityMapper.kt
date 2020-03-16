package com.scallop.data.mappers

import com.scallop.data.entitites.CountryData
import com.scallop.data.entitites.WorldwideData
import com.scallop.domain.entities.CountryEntity
import com.scallop.domain.entities.WorldwideEntity

class CovidDataEntityMapper constructor() {

    fun mapToEntity(data: List<CountryData>): List<CountryEntity> {
        return data.map {
            CountryEntity(
                country = it.country,
                cases = it.cases,
                todayCases = it.todayCases,
                deaths = it.deaths,
                todayDeaths = it.todayDeaths,
                recovered = it.recovered,
                active = it.active,
                critical = it.critical
            )
        }
    }

    fun mapToEntity(data: WorldwideData): WorldwideEntity = WorldwideEntity(
        cases = data.cases,
        deaths = data.deaths,
        recovered = data.recovered
    )
}
