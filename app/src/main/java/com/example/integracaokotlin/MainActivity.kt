package com.example.integracaokotlin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.integracaokotlin.adapter.ItemAdapter
import com.example.integracaokotlin.databinding.ActivityMainBinding
import com.example.integracaokotlin.service.RetrofitClient
import com.example.integracaokotlin.service.safeApiCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.integracaokotlin.service.Result
import kotlinx.coroutines.withContext

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

        // swipe down para recarregar os dados
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = true // inicia o estado de loading
            fetchItems()
        }

    }

    private fun fetchItems(){
        CoroutineScope(Dispatchers.IO).launch {
            // chamada assincrona em uma thread não bloqueante
            val result = safeApiCall { RetrofitClient.apiService.getItems() }

            // insere os dados na view na thread principal
            withContext(Dispatchers.Main) {
                binding.swipeRefreshLayout.isRefreshing = false // finaliza o estado de loading
                when (result) {
                    is Result.Success -> {
                        val adapter = ItemAdapter(result.data, itemClickListener = { item ->
                            startActivity(ItemDetailActivity.newIntent(
                                context = this@MainActivity,
                                itemId = item.id
                            ))
                        })

                        binding.recyclerView.adapter = adapter
                    }
                    is Result.Error -> {
                        Toast.makeText(this@MainActivity, "Erro", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }


}




