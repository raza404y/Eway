package com.example.eway.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.eway.R
import com.example.eway.adapters.ShopsAdapter
import com.example.eway.databinding.FragmentHomeBinding
import com.example.eway.models.ShopsModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private lateinit var binidng: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binidng = FragmentHomeBinding.inflate(layoutInflater)

        return binidng.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val shopList = ArrayList<ShopsModel>()
        setShopsCategories(shopList)

    }

    private fun setShopsCategories(shopList: java.util.ArrayList<ShopsModel>) {


        shopList.add(ShopsModel(R.drawable.cleaning, "Cleaning"))
        shopList.add(ShopsModel(R.drawable.cold_and_juices, "Cold Drinks and Juices"))
        shopList.add(ShopsModel(R.drawable.dairy_breakfast, "Dairy and Breakfast"))
        shopList.add(ShopsModel(R.drawable.dry_masala, "Dry Masala"))
        shopList.add(ShopsModel(R.drawable.home_office, "Home and Office"))
        shopList.add(ShopsModel(R.drawable.instant, "Instant"))
        shopList.add(ShopsModel(R.drawable.instant_frozen, "Instant Frozen"))
        shopList.add(ShopsModel(R.drawable.masala, "Masala"))
        shopList.add(ShopsModel(R.drawable.milma_milk, "Milma Milk"))
        shopList.add(ShopsModel(R.drawable.munchies, "Munchies"))
        shopList.add(ShopsModel(R.drawable.organic_premium, "Organic Premium"))
        shopList.add(ShopsModel(R.drawable.paan_corner, "Paan Corner"))
        shopList.add(ShopsModel(R.drawable.personal_care, "Personal Care"))
        shopList.add(ShopsModel(R.drawable.pet_care, "Pet Care"))
        shopList.add(ShopsModel(R.drawable.atta_rice, "Atta and Rice"))
        shopList.add(ShopsModel(R.drawable.baby, "Baby"))
        shopList.add(ShopsModel(R.drawable.baby_care, "Baby Care"))
        shopList.add(ShopsModel(R.drawable.chicken_meat, "Chicken Meat"))
        shopList.add(ShopsModel(R.drawable.pharma_wellness, "Pharma and Wellness"))
        shopList.add(ShopsModel(R.drawable.sangam_milk, "Sangam Milk"))
        shopList.add(ShopsModel(R.drawable.sauce_spreads, "Sauce and Spreads"))
        shopList.add(ShopsModel(R.drawable.sweet_tooth, "Sweet Tooth"))
        shopList.add(ShopsModel(R.drawable.tea, "Tea"))
        shopList.add(ShopsModel(R.drawable.tea_coffee, "Tea and Coffee"))
        shopList.add(ShopsModel(R.drawable.toned_milk, "Toned Milk"))
        shopList.add(ShopsModel(R.drawable.vegetable, "Vegetables"))


            val adapter = ShopsAdapter(shopList)
            binidng.shopsRecyclerview.adapter = adapter
    }


}