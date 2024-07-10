package com.ozgurzorlu.freshstart.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Food (
    @ColumnInfo("name")
    @SerializedName("isim")
    val name: String,

    @ColumnInfo("calories")
    @SerializedName("kalori")
    val calories: String,

    @ColumnInfo("karb")
    @SerializedName("karbonhidrat")
    val karb: String,

    @ColumnInfo("protein")
    @SerializedName("protein")
    val protein: String,

    @ColumnInfo("fat")
    @SerializedName("yag")
    val fat: String,

    @ColumnInfo("image")
    @SerializedName("gorsel")
    val image: String

    // ROOM için entity ve columninfolar ekleniyor.
)
{
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}




