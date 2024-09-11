package com.example.eway

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.eway.databinding.ProgressDialogBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.dialog.MaterialDialogs
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

    fun getUserId(): String {
        return getAuthInstance().currentUser!!.uid
    }

    fun generateRandomId(length: Int): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        return (1..length)
            .map { chars.random() }
            .joinToString("")
    }
        private var dialog: AlertDialog? = null

        fun showProgressDialog(context: Context, message: String) {
            try {
                val binding = ProgressDialogBinding.inflate(LayoutInflater.from(context))
                binding.dialogTextView.text = message

                dialog = AlertDialog.Builder(context)
                    .setView(binding.root)
                    .create()

                dialog?.setCancelable(false)
                dialog?.show()

                Log.d("ProgressDialog", "Dialog shown with message: $message")
            } catch (e: Exception) {
                Log.e("ProgressDialog", "Error showing progress dialog", e)
            }
        }

        fun hideProgressDialog() {
            try {
                dialog?.dismiss()
                dialog = null
                Log.d("ProgressDialog", "Dialog dismissed")
            } catch (e: Exception) {
                Log.e("ProgressDialog", "Error hiding progress dialog", e)
            }
        }


}