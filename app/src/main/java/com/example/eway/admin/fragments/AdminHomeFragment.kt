package com.example.eway.admin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.eway.Constants
import com.example.eway.R
import com.example.eway.admin.adapters.AdminCategoryAdapter
import com.example.eway.admin.adapters.ProductViewAdapter
import com.example.eway.admin.models.Categories
import com.example.eway.admin.viewmodels.AdminProductViewModel
import com.example.eway.databinding.FragmentAdminHomeBinding
import kotlinx.coroutines.launch


class AdminHomeFragment : Fragment() {
    private lateinit var binding: FragmentAdminHomeBinding
    private lateinit var navController: NavController
    private val viewModel: AdminProductViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAdminHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        binding.menuBtn.setOnClickListener {
            navController.navigate(R.id.action_addProductsFragment_to_adminMenuFragment)
        }
        val categoryList = ArrayList<Categories>()
        setCategories(categoryList)
        getAllProducts("All")
    }

    private fun setCategories(categoryList: ArrayList<Categories>) {
        for (i in 0 until Constants.categoryImagesList.size) {
            categoryList.add(Categories(Constants.categoryImagesList[i], Constants.categoryNamesList[i]))
        }
        val adapter = AdminCategoryAdapter(categoryList,requireContext(), ::onCategorySelected)
        binding.categoryRecyclerView.adapter = adapter


    }

    private fun onCategorySelected(categories: Categories) {
        getAllProducts(categories.shopName)
    }

    private fun getAllProducts(shopName: String) {
        lifecycleScope.launch {
            viewModel.getAllProductFromDatabase(shopName).collect { productList ->
                val adapter = ProductViewAdapter(requireContext())
                binding.productsViewRv.adapter = adapter
                adapter.differ.submitList(emptyList())

                if (productList.isNotEmpty()) {
                    adapter.differ.submitList(productList)
                    binding.shimmerLayout.stopShimmer()
                    binding.shimmerLayout.isVisible = false
                    binding.noProductTextview.isVisible = false
                } else {
                    binding.noProductTextview.isVisible = true
                }
            }

        }
    }

}