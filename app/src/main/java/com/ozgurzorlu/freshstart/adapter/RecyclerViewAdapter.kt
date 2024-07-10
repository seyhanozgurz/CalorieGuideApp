package com.ozgurzorlu.freshstart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ozgurzorlu.freshstart.databinding.RecyclerRowBinding
import com.ozgurzorlu.freshstart.model.Food
import com.ozgurzorlu.freshstart.util.loadImage
import com.ozgurzorlu.freshstart.util.makePlaceHolder
import com.ozgurzorlu.freshstart.view.ListFragmentDirections

class RecyclerViewAdapter(private val foodList : ArrayList<Food>) : RecyclerView.Adapter<RecyclerViewAdapter.FoodHolder>(){

    class FoodHolder(val binding : RecyclerRowBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FoodHolder(binding)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        holder.binding.nameText.text = foodList[position].name
        holder.binding.calorieText.text = foodList[position].calories

        // istenilen besine tıklanıldığında diger fragmenta gecmesi icin gerekenler:
        holder.itemView.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(foodList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
        holder.binding.imageView.loadImage(foodList[position].image, makePlaceHolder(holder.itemView.context))
    }

    fun updateFoodList(newList: List<Food>){
        foodList.clear()
        foodList.addAll(newList)
        notifyDataSetChanged()
    }

}