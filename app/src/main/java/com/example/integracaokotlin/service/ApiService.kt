package com.example.integracaokotlin.service

import com.example.integracaokotlin.model.Item
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("items")
    suspend fun getItems(): List<Item>

    @GET("items/{id}")
    suspend fun getItem(@Path("id") id: String): Item
}