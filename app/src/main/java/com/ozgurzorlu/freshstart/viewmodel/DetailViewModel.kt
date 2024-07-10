package com.ozgurzorlu.freshstart.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ozgurzorlu.freshstart.model.Food
import com.ozgurzorlu.freshstart.roomdb.FoodDatabase
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    val foodLiveData = MutableLiveData<Food>()

    fun getFromRoom(uuid: Int){
        // bunu kullanabilmek için AndroidViewModel tanımlanıyor
        viewModelScope.launch {
            val dao = FoodDatabase(getApplication()).foodDao()
            val food = dao.getFood(uuid)

            foodLiveData.value = food
        }
    }
}