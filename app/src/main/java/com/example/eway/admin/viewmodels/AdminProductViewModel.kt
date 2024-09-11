package com.example.eway.admin.viewmodels

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.eway.Constants
import com.example.eway.Utils
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.ByteArrayOutputStream
import java.io.InputStream

class AdminProductViewModel : ViewModel() {

    private val _isImagesUploaded = MutableStateFlow<Boolean>(false)
    var imagesUploaded = _isImagesUploaded

    private val _downloadAbleUrls = MutableStateFlow<ArrayList<String>>(arrayListOf())
    val downloadedUrls = _downloadAbleUrls

    fun uploadImages(contentResolver: ContentResolver, imagesUri: ArrayList<Uri>) {
        val storageTasks = ArrayList<Task<Uri>>() // List to store all upload tasks
        val downloadedUrls = ArrayList<String>() // List to store download URLs
        imagesUri.forEach { uri ->
            val storageRef = FirebaseStorage.getInstance().getReference()
                .child(Constants.PRODUCT_IMAGES)
                .child(Utils.getUserId())
                .child(Utils.generateRandomId(10))
            // Compress the image before uploading
            val compressedImage = compressImage(contentResolver, uri)
            // Start the upload task and get the download URL task
            val uploadTask = storageRef.putBytes(compressedImage).continueWithTask {
                if (!it.isSuccessful) {
                    throw it.exception ?: Exception("Upload failed")
                }
                storageRef.downloadUrl
            }
            // Add each upload task to the list
            storageTasks.add(uploadTask)
        }
        // Wait for all tasks to complete
        Tasks.whenAllComplete(storageTasks).addOnCompleteListener {
            storageTasks.forEach { task ->
                if (task.isSuccessful) {
                    val downloadUrl = (task.result as Uri).toString()
                    downloadedUrls.add(downloadUrl)
                }
            }
            // After all tasks are complete, update the flow with the URLs
            if (downloadedUrls.size == imagesUri.size) {
                _isImagesUploaded.value = true
                _downloadAbleUrls.value = downloadedUrls
            }
        }
    }

    private fun compressImage(contentResolver: ContentResolver, uri: Uri): ByteArray {
        val inputStream: InputStream? = contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        // Create a ByteArrayOutputStream to hold the compressed image
        val outputStream = ByteArrayOutputStream()
        // Compress the image (reduce the quality to 70% for example)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, outputStream)
        return outputStream.toByteArray() // Return the compressed image as a byte array
    }
}