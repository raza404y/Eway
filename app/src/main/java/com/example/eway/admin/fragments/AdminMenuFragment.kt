package com.example.eway.admin.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.eway.R
import com.example.eway.databinding.FragmentAdminMenuBinding
import com.example.eway.user.activities.AuthenticationActivity
import com.example.eway.user.activities.MainActivity

class AdminMenuFragment : Fragment() {
    private lateinit var binding: FragmentAdminMenuBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAdminMenuBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()

        binding.closeBtn.setOnClickListener {
            navController.navigate(R.id.action_adminMenuFragment_to_addProductsFragment)
        }

        binding.logoutBtn.setOnClickListener {
            startActivity(Intent(requireActivity(),AuthenticationActivity::class.java))
        }

        binding.switchBtn.setOnClickListener {
            startActivity(Intent(requireActivity(),MainActivity::class.java))
        }

    }
}