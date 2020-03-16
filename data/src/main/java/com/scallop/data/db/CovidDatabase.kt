package com.scallop.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.scallop.data.entitites.CountryData
import com.scallop.data.entitites.WorldwideData

@Database(entities = [WorldwideData::class, CountryData::class], version = 1)
abstract class CovidDatabase : RoomDatabase() {
    abstract fun getCovidDao(): CovidDao
}