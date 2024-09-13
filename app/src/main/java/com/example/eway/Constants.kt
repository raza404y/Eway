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

    val categoryImagesList = arrayListOf(
        R.drawable.baseline_category_24,
        R.drawable.cleaning,
        R.drawable.cold_and_juices,
        R.drawable.dairy_breakfast,
        R.drawable.dry_masala,
        R.drawable.home_office,
        R.drawable.instant,
        R.drawable.instant_frozen,
        R.drawable.masala,
        R.drawable.milma_milk,
        R.drawable.munchies,
        R.drawable.organic_premium,
        R.drawable.paan_corner,
        R.drawable.personal_care,
        R.drawable.pet_care,
        R.drawable.atta_rice,
        R.drawable.baby,
        R.drawable.baby_care,
        R.drawable.chicken_meat,
        R.drawable.pharma_wellness,
        R.drawable.sangam_milk,
        R.drawable.sauce_spreads,
        R.drawable.sweet_tooth,
        R.drawable.tea,
        R.drawable.tea_coffee,
        R.drawable.toned_milk,
        R.drawable.vegetable
    )

    val categoryNamesList = arrayListOf(
        "All",
        "Cleaning",
        "Cold Drinks and Juices",
        "Dairy and Breakfast",
        "Dry Masala",
        "Home and Office",
        "Instant",
        "Instant Frozen",
        "Masala",
        "Milma Milk",
        "Munchies",
        "Organic Premium",
        "Paan Corner",
        "Personal Care",
        "Pet Care",
        "Atta and Rice",
        "Baby",
        "Baby Care",
        "Chicken Meat",
        "Pharma and Wellness",
        "Sangam Milk",
        "Sauce and Spreads",
        "Sweet Tooth",
        "Tea",
        "Tea and Coffee",
        "Toned Milk",
        "Vegetables"
    )


}