package com.example.eway.admin.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import com.example.eway.Constants
import com.example.eway.R
import com.example.eway.admin.adapters.SelectedImagesAdapter
import com.example.eway.databinding.FragmentAdminUploadBinding

class AdminUploadFragment : Fragment() {
    lateinit var binding: FragmentAdminUploadBinding
    private val imageUris:ArrayList<Uri> = ArrayList()
    private val selectImage = registerForActivityResult(ActivityResultContracts.GetMultipleContents()){ uriList->
        if (uriList.isNotEmpty()){
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

    }

    private fun selectImagesFromGallery() {
        binding.imagePick.setOnClickListener {
            selectImage.launch("image/*")
        }
    }

    private fun autoCompleteTextViews() {
        val units = ArrayAdapter(requireContext(), R.layout.list_item, Constants.unitList)
        val category =
            ArrayAdapter(requireContext(), R.layout.list_item, Constants.allProductCategory)
        val type = ArrayAdapter(requireContext(), R.layout.list_item, Constants.productTypes)

        binding.unitEt.setAdapter(units)
        binding.productCategoryEt.setAdapter(category)
        binding.productTypeEt.setAdapter(type)

    }

}