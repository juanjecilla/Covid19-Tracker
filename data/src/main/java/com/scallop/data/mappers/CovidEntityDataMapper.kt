package com.scallop.data.mappers

import com.scallop.data.entitites.CountryData
import com.scallop.data.entitites.WorldwideData
import com.scallop.domain.entities.CountryEntity
import com.scallop.domain.entities.WorldwideEntity


class CovidEntityDataMapper {

    fun mapToData(entity: List<CountryEntity>): List<CountryData> {
        return entity.map {
            CountryData(
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

    fun mapToData(entity: WorldwideEntity): WorldwideData = WorldwideData(
        cases = entity.cases,
        deaths = entity.deaths,
        recovered = entity.recovered
    )
}