package com.example.eway.admin.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.eway.R
import com.example.eway.databinding.ActivityAdminBinding

class AdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = resources.getColor(R.color.white)


        val navHostF = supportFragmentManager.findFragmentById(R.id.admin_fragmentContainerView)
        val navController = navHostF!!.findNavController()
        NavigationUI.setupWithNavController(binding.bottomNavbar1,navController)

    }
}