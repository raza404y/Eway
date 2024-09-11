package com.example.eway

object Constants {

    // realtime-database
    const val ALL_USERS = "AllUsers"
    const val USERS = "Users"

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