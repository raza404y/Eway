package com.example.eway

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

object Utils {
    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private var firebaseInstance: FirebaseAuth? = null
    fun getAuthInstance(): FirebaseAuth {
        if (firebaseInstance == null) {
            firebaseInstance = FirebaseAuth.getInstance()
        }
        return firebaseInstance!!
    }
}