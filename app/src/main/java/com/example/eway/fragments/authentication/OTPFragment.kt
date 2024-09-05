package com.example.eway.fragments.authentication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.eway.Utils
import com.example.eway.activities.MainActivity
import com.example.eway.databinding.FragmentOTPBinding
import com.example.eway.models.UsersModel
import com.example.eway.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

class OTPFragment : Fragment() {
    private lateinit var binding: FragmentOTPBinding
    private lateinit var userOTP: String
    private lateinit var time: CountDownTimer
    private val arg: OTPFragmentArgs by navArgs()
    private val authViewModel: AuthViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOTPBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.showPhoneNoTV.text = arg.phoneNo

        sendOTP(arg.phoneNo, requireActivity())


        binding.buttonVerifyOtp.setOnClickListener {

            val userOTP = binding.otpView.otp
            if (userOTP != null) {
                if (userOTP.isEmpty()) {
                    Utils.showToast(requireContext(), "You didn't enter OTP")
                } else if (userOTP.length < 6) {
                    Utils.showToast(requireContext(), "OTP must be 6 characters")
                } else {
                    verifyOTP(userOTP)
                }
            }
        }
    }

    private fun sendOTP(phoneWithCountryCode: String, activity: FragmentActivity) {
        startStopTimer()
        phoneWithCountryCode.let {
            authViewModel.sendOTP(it, activity)
        }
        lifecycleScope.launch {
            authViewModel.otpSent.collect { otpSent ->
                if (otpSent) {
                    buttonState(true, 1f, "Verify OTP")
                    time.cancel()
                }
            }
        }
    }
    @SuppressLint("SetTextI18n")
    private fun verifyOTP(userOTP: String) {
        buttonState(false, .5f, "Verifying...")
        authViewModel.signInWithPhoneAuthCredential(userOTP, requireActivity(),arg.phoneNo)
        lifecycleScope.launch {
            authViewModel.verified.collect { verified ->
                if (verified == true) {
                    Utils.showToast(requireContext(), "Logged in Successfully")
                    buttonState(false, 1f, "Verification successful")
                    startActivity(Intent(requireActivity(),MainActivity::class.java))
                } else if (verified == false) {
                    buttonState(true, 1f, "Verify OTP")
                }
            }
        }
    }

    private fun startStopTimer() {
        time = object : CountDownTimer(60000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                buttonState(false, .5f, "Sending OTP...${millisUntilFinished / 1000}s")
            }

            override fun onFinish() {
                buttonState(true, 1f, "Verify OTP")
            }
        }.start()
    }

    @SuppressLint("SetTextI18n")
    private fun buttonState(enable: Boolean, alpha: Float, text: String) {
        binding.buttonVerifyOtp.isEnabled = enable
        binding.buttonVerifyOtp.alpha = alpha
        binding.buttonVerifyOtp.text = text
    }
}