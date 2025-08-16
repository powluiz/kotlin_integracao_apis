package com.example.integracaokotlin.model

data class Location (
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
    val location: Location,
)

data class Item (
    val id: String,
    val value: ItemValue
)