package com.example.eway.admin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eway.databinding.AdminShopProductCategoryRvBinding
import com.example.eway.databinding.ShopProductCategoryRvBinding
import com.example.eway.user.models.ShopsModel

class AdminCategoryAdapter(private val list: ArrayList<ShopsModel>): RecyclerView.Adapter<AdminCategoryAdapter.ViewHolder>() {
    class ViewHolder(val binding: AdminShopProductCategoryRvBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(AdminShopProductCategoryRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val i = list[position]
        holder.binding.shopName.text = i.shopName
        holder.binding.shopImage.setImageResource(i.shopImage)
    }
}