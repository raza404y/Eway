package com.example.eway.admin.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eway.databinding.ItemProductImagesBinding

class SelectedImagesAdapter(private val list: ArrayList<Uri>) :
    RecyclerView.Adapter<SelectedImagesAdapter.IViewHolder>() {
    inner class IViewHolder(val binding: ItemProductImagesBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IViewHolder {
        return IViewHolder(
            ItemProductImagesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        val currentImage = list[position]
        holder.binding.productImageView.setImageURI(currentImage)

        holder.binding.closeBtn.setOnClickListener {
            if (position < list.size){
                list.removeAt(position)
                notifyItemRemoved(position)
            }
        }
    }
}