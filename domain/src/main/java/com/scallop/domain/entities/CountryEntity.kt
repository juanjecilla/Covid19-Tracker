package com.scallop.domain.entities

data class CountryEntity(
    val country: String,
    val cases: Int,
    val todayCases: Int,
    val deaths: Int,
    val todayDeaths: Int,
    val recovered: Int,
    val active: Int,
    val critical: Int
)
