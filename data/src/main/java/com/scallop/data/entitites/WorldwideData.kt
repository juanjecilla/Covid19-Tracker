package com.scallop.data.entitites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "worldwide")
data class WorldwideData(
    @PrimaryKey(autoGenerate = true) @SerializedName("id") var id: Long,
    val cases: Int,
    val deaths: Int,
    val recovered: Int
)