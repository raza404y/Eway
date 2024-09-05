package com.example.eway.user.fragments.authentication

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
import com.example.eway.R
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

        binding.continueBtn.setOnClickListener {
            findNavController().navigate(R.id.action_onboardFragment_to_phoneNoFragment)
        }

        lifecycleScope.launch {
            authViewModel.currentUser.collect { isLoggedIn ->
                if (isLoggedIn) {
                    startActivity(Intent(requireActivity(), MainActivity::class.java))
                    requireActivity().finish()
                }
            }
        }

        return binding.root
    }
}