package com.example.eway.user.fragments.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.eway.R
import com.example.eway.Utils
import com.example.eway.databinding.FragmentMenuBinding
import com.example.eway.user.activities.AuthenticationActivity


class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMenuBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()

        binding.closeBtn.setOnClickListener {
            navController.navigate(R.id.action_menuFragment_to_homeFragments)
        }

        binding.logoutBtn.setOnClickListener {
            Utils.getAuthInstance().signOut()
            startActivity(Intent(requireActivity(),AuthenticationActivity::class.java))
        }

    }

}