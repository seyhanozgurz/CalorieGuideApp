package com.ozgurzorlu.freshstart.service

import com.ozgurzorlu.freshstart.model.Food
import retrofit2.http.GET

interface FoodAPI {
    // https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json
    // https://raw.githubusercontent.com/seyhanozgurz/JSONDataset/main/besinler.json
    @GET("seyhanozgurz/JSONDataset/main/besinler.json")
    suspend fun getFood() : List<Food>

}