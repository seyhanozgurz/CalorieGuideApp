package com.ozgurzorlu.freshstart.service

import com.ozgurzorlu.freshstart.model.Food
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FoodApiService {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(FoodAPI::class.java)

    suspend fun getData() : List<Food>{
        return retrofit.getFood()
    }
}
