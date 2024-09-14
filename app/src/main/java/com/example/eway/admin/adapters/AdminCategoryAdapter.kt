package com.example.eway.admin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.eway.admin.models.Categories
import com.example.eway.databinding.AdminShopProductCategoryRvBinding
import com.example.eway.user.models.ShopsModel

class AdminCategoryAdapter(private val list: ArrayList<Categories>,val context: Context,val onCategorySelected: (Categories) -> Unit): RecyclerView.Adapter<AdminCategoryAdapter.ViewHolder>() {
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

        holder.itemView.setOnClickListener {
            onCategorySelected(i)
        }
    }
}