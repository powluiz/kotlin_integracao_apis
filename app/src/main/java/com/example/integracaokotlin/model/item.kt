package com.example.integracaokotlin.model

data class ItemLocation (
    val latitude: Double,
    val longitude: Double,
    val name: String,
)

data class ItemValue (
    val id: String,
    val name: String,
    val surname: String,
    val profession: String,
    val imageUrl: String,
    val age: Int,
    val location: ItemLocation,
)

data class Item (
    val id: String,
    val value: ItemValue
)