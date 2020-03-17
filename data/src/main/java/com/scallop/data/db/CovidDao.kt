package com.scallop.data.db

import androidx.room.Dao
import androidx.room.Query
import com.scallop.data.entitites.CountryData
import com.scallop.data.entitites.WorldwideData
import io.reactivex.Observable

@Dao
interface CovidDao {

    @Query("Select * from countries")
    fun getAllArticles(): Observable<List<CountryData>>

    @Query("Select * from worldwide")
    fun getMovieDetail(): Observable<WorldwideData>
}