package com.example.eway.admin.viewmodels

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.eway.Constants
import com.example.eway.ProductModel
import com.example.eway.Utils
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.util.UUID

class AdminProductViewModel : ViewModel() {

    private val _isImagesUploaded = MutableStateFlow<Boolean>(false)
    var imagesUploaded = _isImagesUploaded

    private val _downloadAbleUrls = MutableStateFlow<ArrayList<String>>(arrayListOf())
    val downloadedUrls = _downloadAbleUrls

    private val _productUploadedSuccessfully = MutableStateFlow<Boolean>(false)
    val productUploadedSuccessfully = _productUploadedSuccessfully



    fun uploadImagesToStorage(contentResolver: ContentResolver, imagesUri: ArrayList<Uri>) {
        val downloadedUrls = ArrayList<String>()

        imagesUri.forEach {
            val myRef = FirebaseStorage.getInstance().getReference().child(Constants.PRODUCT_IMAGES)
                .child(Utils.getUserId())
                .child(UUID.randomUUID().toString())
            val cmpImg = compressImage(contentResolver, it)
            myRef.putBytes(cmpImg).continueWithTask {
                myRef.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val url = task.result.toString()
                    downloadedUrls.add(url)
                }
                if (downloadedUrls.size == imagesUri.size) {
                    _isImagesUploaded.value = true
                    _downloadAbleUrls.value = downloadedUrls
                }
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

    fun saveProductToDatabase(productModel: ProductModel) {
        FirebaseDatabase.getInstance()
            .getReference(Constants.ADMINS)
            .child(Constants.ALL_PRODUCTS)
            .setValue(productModel).addOnSuccessListener {
                FirebaseDatabase.getInstance()
                    .getReference(Constants.ADMINS)
                    .child(Constants.PRODUCT_CATEGORY)
                    .setValue(productModel).addOnSuccessListener {
                        FirebaseDatabase.getInstance()
                            .getReference(Constants.ADMINS)
                            .child(Constants.PRODUCT_TYPE)
                            .setValue(productModel).addOnSuccessListener {
                                    _productUploadedSuccessfully.value = true
                            }
                    }
            }

    }
}