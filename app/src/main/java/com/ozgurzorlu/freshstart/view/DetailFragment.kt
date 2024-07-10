package com.ozgurzorlu.freshstart.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ozgurzorlu.freshstart.databinding.FragmentDetailBinding
import com.ozgurzorlu.freshstart.util.loadImage
import com.ozgurzorlu.freshstart.util.makePlaceHolder
import com.ozgurzorlu.freshstart.viewmodel.DetailViewModel


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel : DetailViewModel
    private var foodId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        arguments?.let {
            foodId = DetailFragmentArgs.fromBundle(it).foodId
        }
        viewModel.getFromRoom(foodId)

        observeLiveData()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeLiveData(){
        viewModel.foodLiveData.observe(viewLifecycleOwner){
            binding.foodNameText.text = it.name
            binding.foodFatText.text = it.fat
            binding.foodCarbText.text = it.karb
            binding.foodCaloriesText.text = it.calories
            binding.foodProteinText.text = it.protein
            binding.imageView2.loadImage(it.image, makePlaceHolder(requireContext()))
        }
    }


}