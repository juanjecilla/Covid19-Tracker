package com.scallop.data.api

import com.scallop.data.entitites.CountryData
import com.scallop.data.entitites.WorldwideData
import io.reactivex.Observable
import retrofit2.http.GET

interface CovidApi {

    @GET("/all")
    fun getWorldwideInfo(): Observable<WorldwideData>

    @GET("/countries")
    fun getInfoByCountry(): Observable<List<CountryData>>
}