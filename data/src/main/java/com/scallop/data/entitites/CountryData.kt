package com.scallop.data.entitites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "countries")
data class CountryData(
    @PrimaryKey(autoGenerate = true) @SerializedName("id") var id: Long,
    val country: String,
    val cases: Int,
    val todayCases: Int,
    val deaths: Int,
    val todayDeaths: Int,
    val recovered: Int,
    val active: Int,
    val critical: Int
)