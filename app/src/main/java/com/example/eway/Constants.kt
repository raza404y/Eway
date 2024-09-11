package com.example.eway

object Constants {

    // realtime-database
    const val ALL_USERS = "AllUsers"
    const val USERS = "Users"
    const val ADMINS = "admins"
    const val ADMIN_INFO = "adminInfo"
    var ALL_PRODUCTS = "all_products/${Utils.generateRandomId(10)}"
    var PRODUCT_CATEGORY = "product_category/${Utils.generateRandomId(10)}"
    var PRODUCT_TYPE = "product_type/${Utils.generateRandomId(10)}"


    // user-roles
    const val SHARED_PREF_NAME = "role"
    const val USER_ROLE = "buyer"

    // storage
    const val PRODUCT_IMAGES = "product_images"

    val allProductCategory =
        listOf(
            "All","Vegetables & fruits","Dairy & breakfast","Munchies","Cold Drinks & juices","Sweets",
            "Dry fruits",
            "Sauces & spreads",
            "Chicken meat",
            "Organic Premium",
            "Pharma",
            "Cleaning essentials",
            "Home & office",
            "Personal Care",
            "Pet Care"
        )

    val unitList = listOf(
        "kg",
        "Ltr",
        "packets",
        "pieces"
    )

    val productTypes = listOf(
        "Milk, Curd & Paneer",
        "Vegetables",
        "Chips",
        "Fruits",
        "Salt & Sugar",
        "Noodles",
        "Cooking oil",
        "Eggs",
        "Chocolates",
        "Bread & Buffer",
        "Ice Cream",
        "Cake",
        "Ghee",
        "Water"
    )

}