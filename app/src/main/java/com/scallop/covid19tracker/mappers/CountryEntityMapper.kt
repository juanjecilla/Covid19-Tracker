package com.scallop.covid19tracker.mappers

import com.scallop.covid19tracker.model.Country
import com.scallop.domain.common.Mapper
import com.scallop.domain.entities.CountryEntity

class CountryEntityMapper : Mapper<List<CountryEntity>, List<Country>>() {
    override fun mapFrom(from: List<CountryEntity>): List<Country> {
        return from.map {
            Country(
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
}