package com.ozgurzorlu.freshstart.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ozgurzorlu.freshstart.model.Food

@Dao
interface FoodDAO {

    @Query("SELECT * FROM food")
    suspend fun getAllFood() : List<Food>

    @Query("SELECT * FROM food WHERE uuid = :foodId")
    suspend fun getFood(foodId : Int) : Food

    @Query("DELETE FROM food")
    suspend fun deleteAllFood()

    @Insert
    suspend fun insertAllFood(vararg food: Food) : List<Long> //eklenen besinlerin id sini donuyor.
    // vararg kullanilinca istedigin kadar deger yollanabiliyor.
}