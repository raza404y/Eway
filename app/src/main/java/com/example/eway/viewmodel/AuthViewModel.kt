package com.example.eway.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.example.eway.Constants
import com.example.eway.Utils
import com.example.eway.models.UsersModel
import com.google.firebase.Firebase
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.database
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.concurrent.TimeUnit

class AuthViewModel : ViewModel() {

    private val _verificationId = MutableStateFlow<String?>(null)
    private val _otpSent = MutableStateFlow<Boolean>(false)
    val otpSent = _otpSent
    private val _verified = MutableStateFlow<Boolean?>(null)
    val verified = _verified
    private val _currentUser = MutableStateFlow<Boolean>(false)
    val currentUser = _currentUser

    init {
        if (Utils.getAuthInstance().currentUser != null){
            _currentUser.value = true
        }
    }

    fun sendOTP(phoneNo: String, activity: Activity) {

        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            }

            override fun onVerificationFailed(e: FirebaseException) {
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                _verificationId.value = verificationId
                _otpSent.value = true
            }
        }


        val options = PhoneAuthOptions.newBuilder(Utils.getAuthInstance())
            .setPhoneNumber(phoneNo) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun signInWithPhoneAuthCredential(userOTP: String, activity: Activity, phoneNo: String) {
        val credential = PhoneAuthProvider.getCredential(_verificationId.value.toString(), userOTP)
        Utils.getAuthInstance().signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val users = UsersModel(Utils.getUserId(),phoneNo,null)
                    uploadingUserInfoToDatabase(users)
                    _verified.value = true
                    _verified.value = null
                } else {
                    _verified.value = false
                    _verified.value = null
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Utils.showToast(activity, "OTP you entered is wrong")
                    }
                }
            }
        }

    private fun uploadingUserInfoToDatabase(users: UsersModel) {
        Firebase.database.reference
            .child(Constants.ALL_USERS)
            .child(Constants.USERS)
            .child(Utils.getUserId())
            .setValue(users)
    }

}