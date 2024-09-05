package com.example.eway.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eway.databinding.ShopProductCategoryRvBinding
import com.example.eway.models.ShopsModel

class ShopsAdapter(private val shopList: ArrayList<ShopsModel>) :
    RecyclerView.Adapter<ShopsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ShopProductCategoryRvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(shopsModel: ShopsModel) {
            shopsModel.apply {
                binding.shopImage.setImageResource(shopImage)
                binding.shopName.text = shopName
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ShopProductCategoryRvBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val i = shopList[position]
        holder.bindData(i)
    }
}