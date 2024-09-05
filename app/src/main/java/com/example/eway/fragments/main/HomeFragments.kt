package com.example.eway.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.eway.R
import com.example.eway.databinding.FragmentHomeFragmentsBinding


class HomeFragments : Fragment() {

    lateinit var binidng: FragmentHomeFragmentsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_fragments, container, false)
    }


}