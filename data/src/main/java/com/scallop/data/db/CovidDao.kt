package com.scallop.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.scallop.data.entitites.CountryData
import com.scallop.data.entitites.WorldwideData
import io.reactivex.Observable

@Dao
interface CovidDao {

    @Query("Select * from countries")
    fun getInfoByCountry(): Observable<List<CountryData>>

    @Query("Select * from worldwide")
    fun getWorldwideInfo(): Observable<WorldwideData>

    @Insert
    fun setInfoByCountry(list: List<CountryData>)

    @Insert
    fun setWorldwideInfo(worldwideData: WorldwideData)

    @Query("Delete from worldwide")
    fun clearWorldwideInfo()

    @Query("Delete from countries")
    fun clearInfoByCountry()
}