package com.example.eway.admin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.eway.ProductModel
import com.example.eway.databinding.AdminProductRvDesignBinding

class ProductViewAdapter(val context: Context) : RecyclerView.Adapter<ProductViewAdapter.ProductViewHolder>() {
    inner class ProductViewHolder(val binding: AdminProductRvDesignBinding) : RecyclerView.ViewHolder(binding.root)


    private val diffCallBack = object : DiffUtil.ItemCallback<ProductModel>() {
        override fun areItemsTheSame(oldItem:ProductModel, newItem: ProductModel): Boolean {
            return oldItem.productRandomId == newItem.productRandomId
        }

        override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
            return oldItem == newItem
        }

    }
     val differ = AsyncListDiffer(this, diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(AdminProductRvDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val p = differ.currentList[position]
        val productImg = p.productImagesURIList

        val imageArray = ArrayList<SlideModel>()
        for (i in 0 until productImg?.size!!) {
            imageArray.add(SlideModel(p.productImagesURIList!![i].toString()))
        }

        holder.apply {
            binding.imageSlider.setImageList(imageArray,ScaleTypes.CENTER_CROP)
            binding.productName.text = p.productTitle
            binding.productPrice.text = "${p.productPrice}$"
            val quantity = p.productQuantity.toString() + p.productUnit
            binding.productQuantity.text = quantity
        }
    }
}