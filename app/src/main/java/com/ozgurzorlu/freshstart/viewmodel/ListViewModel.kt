package com.ozgurzorlu.freshstart.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ozgurzorlu.freshstart.model.Food
import com.ozgurzorlu.freshstart.roomdb.FoodDatabase
import com.ozgurzorlu.freshstart.service.FoodApiService
import com.ozgurzorlu.freshstart.util.PrivateSharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListViewModel(application: Application) : AndroidViewModel(application) {

    val foods = MutableLiveData<List<Food>>()
    val foodErrorMessage = MutableLiveData<Boolean>()
    val foodLoading = MutableLiveData<Boolean>()

    private val foodApiService = FoodApiService()
    private val privateSharedPreferences = PrivateSharedPreferences(getApplication())

    fun getInternetData(){
        foodLoading.value = true

        viewModelScope.launch(Dispatchers.IO) {
            val foodList  = foodApiService.getData()
            withContext(Dispatchers.Main){
                foodLoading.value = false
                saveToRoom(foodList)
                //Toast.makeText(getApplication(),"Veriler internetten geldi",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showFoods(foodList : List<Food>){
        foods.value = foodList
        foodErrorMessage.value = false
        foodLoading.value = false
    }

    private fun saveToRoom(foodList : List<Food>){

        viewModelScope.launch {
            val dao = FoodDatabase(getApplication()).foodDao()
            dao.deleteAllFood()

            // vararg istenildiginde list gondermek istersek bu sekilde yazilabilir:
            val uuidList = dao.insertAllFood(*foodList.toTypedArray())
            for (i in foodList.indices){
                foodList[i].uuid = uuidList[i].toInt()
            }

            showFoods(foodList)
        }

        privateSharedPreferences.saveTime(System.nanoTime())
    }

    private val refreshFrequency = 10 * 60 * 1000 * 1000 * 1000L // 10dk, nanosaniye cinsinden

    fun refreshData(){
        val savedTime = privateSharedPreferences.getTime()

        if (savedTime != null && savedTime != 0L && System.nanoTime() - savedTime > refreshFrequency){
            getInternetData()
        }
        else{
            getRoomData()
        }

    }

    private fun getRoomData(){
        foodLoading.value = true

        viewModelScope.launch(Dispatchers.IO) {
            val foodList = FoodDatabase(getApplication()).foodDao().getAllFood()
            withContext(Dispatchers.Main){
                showFoods(foodList)
                //Toast.makeText(getApplication(),"Veriler Roomdan geldi",Toast.LENGTH_SHORT).show()
            }
        }
    }




}