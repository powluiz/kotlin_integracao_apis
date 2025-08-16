package com.example.integracaokotlin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.integracaokotlin.databinding.ActivityMainBinding
import com.example.integracaokotlin.service.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import safeApiCall

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }


    override fun onResume() {
        super.onResume()

        fetchItems()
    }

    private fun setupView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun fetchItems(){

        CoroutineScope(Dispatchers.IO).launch {
            val result = safeApiCall { RetrofitClient.apiService.getItems() }
            Log.d("Log", "$result")
        }

    }


}




