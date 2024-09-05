package com.example.eway.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.eway.R
import com.example.eway.databinding.FragmentOnboardBinding


class OnboardFragment : Fragment() {
    private lateinit var binding: FragmentOnboardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOnboardBinding.inflate(layoutInflater)

        binding.continueBtn.setOnClickListener{
            findNavController().navigate(R.id.action_onboardFragment_to_phoneNoFragment)
        }
        return binding.root
    }
}