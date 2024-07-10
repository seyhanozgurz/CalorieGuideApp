package com.ozgurzorlu.freshstart.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ozgurzorlu.freshstart.adapter.RecyclerViewAdapter
import com.ozgurzorlu.freshstart.databinding.FragmentListBinding
import com.ozgurzorlu.freshstart.viewmodel.ListViewModel

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel : ListViewModel

    private val recyclerViewAdapter = RecyclerViewAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ListViewModel::class.java]
        viewModel.refreshData()

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = recyclerViewAdapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.recyclerView.visibility = View.GONE
            binding.errorText.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE

            viewModel.getInternetData()
            binding.swipeRefreshLayout.isRefreshing = false // ustte refreshing yazmasin diye
        }

        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.foods.observe(viewLifecycleOwner){
            recyclerViewAdapter.updateFoodList(it)
            binding.recyclerView.visibility = View.VISIBLE
        }
        viewModel.foodErrorMessage.observe(viewLifecycleOwner){
            if (it){
                binding.recyclerView.visibility = View.GONE
                binding.errorText.visibility = View.VISIBLE
            } else {
                binding.errorText.visibility = View.GONE
            }
        }
        viewModel.foodLoading.observe(viewLifecycleOwner){
            if (it){
                binding.progressBar.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
                binding.errorText.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}