package com.scallop.covid19tracker.mappers

import com.scallop.covid19tracker.model.Worldwide
import com.scallop.domain.common.Mapper
import com.scallop.domain.entities.WorldwideEntity

class WorldwideEntityMapper : Mapper<WorldwideEntity, Worldwide>() {
    override fun mapFrom(from: WorldwideEntity): Worldwide =
        Worldwide(
            cases = from.cases,
            deaths = from.deaths,
            recovered = from.recovered
        )
}