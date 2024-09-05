package com.example.eway.user.fragments.authentication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.eway.Constants
import com.example.eway.R
import com.example.eway.admin.activities.AdminActivity
import com.example.eway.user.activities.MainActivity
import com.example.eway.databinding.FragmentOnboardBinding
import com.example.eway.user.viewmodel.AuthViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class OnboardFragment : Fragment() {
    private lateinit var binding: FragmentOnboardBinding
    private val authViewModel: AuthViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOnboardBinding.inflate(layoutInflater)
        val navController = findNavController()
        binding.buyerBtn.setOnClickListener {
            val userRole = "buyer"
            val action = OnboardFragmentDirections.actionOnboardFragmentToPhoneNoFragment(userRole)
            navController.navigate(action)
        }
        val sharedPreferences = requireContext().getSharedPreferences(Constants.SHARED_PREF_NAME,Context.MODE_PRIVATE)
        val userRole = sharedPreferences.getString(Constants.USER_ROLE,"buyer")
        if (userRole == "buyer") {
            lifecycleScope.launch {
                authViewModel.currentUser.collect { isLoggedIn ->
                    if (isLoggedIn) {
                        startActivity(Intent(requireActivity(), MainActivity::class.java))
                        requireActivity().finish()
                    }
                }
            }
        }else{
            lifecycleScope.launch {
                authViewModel.currentUser.collect { isLoggedIn ->
                    if (isLoggedIn) {
                        startActivity(Intent(requireActivity(), AdminActivity::class.java))
                        requireActivity().finish()
                    }
                }
            }
        }

        binding.sellerBtn.setOnClickListener {
            lifecycleScope.launch {
                authViewModel.currentUser.collect { isLoggedIn ->
                    if (isLoggedIn) {
                        startActivity(Intent(requireActivity(), MainActivity::class.java))
                        requireActivity().finish()
                    } else {
                        val userRole = "seller"
                        val action = OnboardFragmentDirections.actionOnboardFragmentToPhoneNoFragment(userRole)
                        navController.navigate(action)
                    }
                }
            }
        }

        return binding.root
    }
}