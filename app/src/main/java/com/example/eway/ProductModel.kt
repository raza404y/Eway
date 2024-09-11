package com.example.eway

import android.net.Uri
import java.util.UUID

data class ProductModel(
    var productTitle: String? = null,
    var productQuantity: Int? = null,
    var productUnit: String? = null,
    var productPrice: Int? = null,
    var noOfStocks: Int? = null,
    var productCategory: String? = null,
    var productType: String? = null,

    var itemCount: Int? = null,
    var adminId: String? = null,
    var productImagesURIList: ArrayList<String>? = null,
    var productRandomId: String = Utils.generateRandomId(10)
)
