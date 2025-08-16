package com.example.integracaokotlin.service

import com.example.integracaokotlin.model.Item
import retrofit2.http.GET

interface ApiService {

    @GET("items")
    suspend fun getItems(): List<Item>
}