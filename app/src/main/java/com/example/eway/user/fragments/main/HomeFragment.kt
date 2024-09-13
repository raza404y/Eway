package com.example.eway.user.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.eway.Constants
import com.example.eway.R
import com.example.eway.user.adapters.ShopsAdapter
import com.example.eway.databinding.FragmentHomeBinding
import com.example.eway.user.models.ShopsModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.reflect.Array


class HomeFragment : Fragment() {

    private lateinit var binidng: FragmentHomeBinding
    lateinit var navController: NavController
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

        navController = findNavController()

        val shopList = ArrayList<ShopsModel>()
        setShopsCategories(shopList)


        binidng.menuBtn.setOnClickListener {
            navController.navigate(R.id.action_homeFragments_to_menuFragment)
        }

    }

    private fun setShopsCategories(shopList: java.util.ArrayList<ShopsModel>) {

        for (i in 0 until  Constants.categoryNamesList.size) {
            shopList.add(ShopsModel(Constants.categoryImagesList[i], Constants.categoryNamesList[i]))
        }

        val adapter = ShopsAdapter(shopList)
        binidng.shopsRecyclerview.adapter = adapter
    }


}