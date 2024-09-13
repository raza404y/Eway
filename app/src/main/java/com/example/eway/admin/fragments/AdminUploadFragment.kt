package com.example.eway.admin.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.eway.Constants
import com.example.eway.ProductModel
import com.example.eway.R
import com.example.eway.Utils
import com.example.eway.admin.activities.AdminActivity
import com.example.eway.admin.adapters.SelectedImagesAdapter
import com.example.eway.admin.viewmodels.AdminProductViewModel
import com.example.eway.databinding.FragmentAdminUploadBinding
import kotlinx.coroutines.launch

class AdminUploadFragment : Fragment() {
    lateinit var binding: FragmentAdminUploadBinding
    private val imageUris: ArrayList<Uri> = ArrayList()
    private val viewModel: AdminProductViewModel by viewModels()
    private val selectImage = registerForActivityResult(ActivityResultContracts.GetMultipleContents()) { uriList ->
        if (uriList.isNotEmpty()) {
            val takeFiveImages = uriList.take(5)
            imageUris.clear()
            imageUris.addAll(takeFiveImages)
            binding.productImageRecyclerView.adapter = SelectedImagesAdapter(imageUris)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAdminUploadBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        autoCompleteTextViews()
        selectImagesFromGallery()
        uploadProductToDatabase()

        viewLifecycleOwner.lifecycleScope.launch {
                viewModel.productUploadedSuccessfully.collect { isUploaded ->
                    if (isUploaded) {
                        Utils.hideProgressDialog()
                        Utils.showToast(requireContext(), "Your product is Live now.")
                        setEmptyAllFields()
                        startActivity(Intent(requireActivity(),AdminActivity::class.java))
                    }
            }
        }

    }

    private fun selectImagesFromGallery() {
        binding.imagePick.setOnClickListener {
            selectImage.launch("image/*")
        }
    }

    private fun autoCompleteTextViews() {
        val units = ArrayAdapter(requireContext(), R.layout.list_item, Constants.unitList)
        val category = ArrayAdapter(requireContext(), R.layout.list_item, Constants.categoryNamesList)
        val type = ArrayAdapter(requireContext(), R.layout.list_item, Constants.productTypes)

        binding.unitEt.setAdapter(units)
        binding.productCategoryEt.setAdapter(category)
        binding.productTypeEt.setAdapter(type)

        binding.unitEt.setOnFocusChangeListener { _, _ ->
            binding.unitEt.showDropDown()
        }
        binding.productCategoryEt.setOnFocusChangeListener { _, _ ->
            binding.productCategoryEt.showDropDown()
        }
        binding.productTypeEt.setOnFocusChangeListener { _, _ ->
            binding.productTypeEt.showDropDown()
        }

    }

    private fun uploadProductToDatabase() {
        binding.uploadBtn.setOnClickListener {
            val title = binding.productTitleEt.text.toString().trim()
            val quantity = binding.quantityEt.text.toString().trim()
            val unit = binding.unitEt.text.toString().trim()
            val price = binding.priceEt.text.toString().trim()
            val noOfStocks = binding.noOfStockEt.text.toString().trim()
            val category = binding.productCategoryEt.text.toString().trim()
            val type = binding.productTypeEt.text.toString().trim()

            when {
                title.isEmpty() -> Utils.showToast(requireContext(), "Enter title.")
                quantity.isEmpty() -> Utils.showToast(requireContext(), "Enter Quantity.")
                unit.isEmpty() -> Utils.showToast(requireContext(), "Select an Unit.")
                price.isEmpty() -> Utils.showToast(requireContext(), "Enter Price.")
                noOfStocks.isEmpty() -> Utils.showToast(requireContext(), "Enter title no of stocks.")
                category.isEmpty() -> Utils.showToast(requireContext(), "Select an Category.")
                type.isEmpty() -> Utils.showToast(requireContext(), "Select an product type")
                imageUris.isEmpty() -> Utils.showToast(requireContext(), "Select some product images.")
                else -> {
                    val productModel = ProductModel(
                        productTitle = title,
                        productQuantity = quantity.toInt(),
                        productUnit = unit,
                        productPrice = price.toInt(),
                        noOfStocks = noOfStocks.toInt(),
                        productCategory = category,
                        productType = type,
                        itemCount = 0,
                        adminId = Utils.getUserId()
                    )
                    uploadingImagesArray(productModel)
                }
            }
        }
    }

    private fun uploadingImagesArray(productModel: ProductModel) {
        val contentResolver = requireContext().contentResolver
        viewModel.uploadImagesToStorage(contentResolver, imageUris)
        lifecycleScope.launch {
            viewModel.imagesUploaded.collect {
                if (it) {
                    gettingImagesUrls(productModel)
                }
            }
        }
    }

    private fun gettingImagesUrls(productModel: ProductModel) {
        Utils.showProgressDialog(requireContext(), "Getting ready for live...")
        lifecycleScope.launch {
            viewModel.downloadedUrls.collect { imgUrlsList ->
                productModel.productImagesURIList = imgUrlsList
                savingProductToDatabase(productModel)
            }
        }
    }

    private fun savingProductToDatabase(productModel: ProductModel) {
        viewModel.saveProductToDatabase(productModel)
    }

    @SuppressLint("SetTextI18n")
    fun setEmptyAllFields() {
        imageUris.clear()
        binding.productTitleEt.setText("")
        binding.quantityEt.setText("")
        binding.unitEt.setText("")
        binding.priceEt.setText("")
        binding.noOfStockEt.setText("")
        binding.productCategoryEt.setText("")
        binding.productTypeEt.setText("")

    }
}
