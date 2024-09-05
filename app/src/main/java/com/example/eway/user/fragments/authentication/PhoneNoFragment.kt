package com.example.eway.user.fragments.authentication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.eway.Utils
import com.example.eway.databinding.FragmentPhoneNoBinding


class PhoneNoFragment : Fragment() {
    private lateinit var binding: FragmentPhoneNoBinding
    private lateinit var phoneWithCountryCode: String
    private var validNumber = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhoneNoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ccp.registerCarrierNumberEditText(binding.phoneNoEt)

        binding.ccp.setPhoneNumberValidityChangeListener { isValidNumber ->
           validNumber = handlePhoneNumberValidity(isValidNumber)
        }
        setupPhoneNoEditText()
        setupGetOtpButton()
    }

    private fun handlePhoneNumberValidity(validNumber: Boolean):Boolean {
        return validNumber
    }

    private fun setupGetOtpButton() {
        binding.buttonGetOtp.setOnClickListener {
            phoneWithCountryCode = binding.ccp.fullNumberWithPlus
                if (!validNumber) {
                    Utils.showToast(requireContext(),"Incorrect Phone No.")
                } else {
                    val action = PhoneNoFragmentDirections.actionPhoneNoFragmentToOTPFragment(phoneWithCountryCode)
                    findNavController().navigate(action)
            }
        }
    }

    private fun setupPhoneNoEditText() {
        binding.phoneNoEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val phoneNo = s.toString().trim()
                if (phoneNo.isBlank()) {
                    binding.buttonGetOtp.isEnabled = false
                    binding.buttonGetOtp.alpha = 0.5f
                } else {
                    binding.buttonGetOtp.isEnabled = true
                    binding.buttonGetOtp.alpha = 1f
                }
            }
        })
    }
}