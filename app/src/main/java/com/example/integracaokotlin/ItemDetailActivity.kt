package com.example.integracaokotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.integracaokotlin.databinding.ActivityItemDetailBinding
import com.example.integracaokotlin.model.Item
import com.example.integracaokotlin.service.Result
import com.example.integracaokotlin.service.RetrofitClient
import com.example.integracaokotlin.service.safeApiCall
import com.example.integracaokotlin.ui.loadUrl
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItemDetailActivity : AppCompatActivity() {

    private lateinit var item: Item
    private lateinit var binding: ActivityItemDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemDetailBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        setupView()
        loadItem()

    }

    private fun setupView() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun loadItem() {
        val itemId = intent.getStringExtra(ARG_ITEM_ID) ?: ""

        CoroutineScope(Dispatchers.IO).launch {
            val result = safeApiCall { RetrofitClient.apiService.getItem(itemId) }

            // atualiza a view na thread principal
            withContext(Dispatchers.Main) {
                when (result) {
                    is Result.Success -> {
                        item = result.data;
                        handleSuccess()
                    }
                    is Result.Error -> {
                        Toast.makeText(
                            this@ItemDetailActivity,
                            "Um erro ocorreu ao obter os dados do usuário",
                            Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }
    }

    private fun handleSuccess () {
        binding.name.text = item.value.name
        binding.age.text = getString(R.string.item_age, item.value.age.toString())
        binding.profession.setText(item.value.profession)
        binding.image.loadUrl(item.value.imageUrl)
    }


    companion object {
        private const val ARG_ITEM_ID = "item_id"
        fun newIntent(
            context: Context,
            itemId: String
        ) = Intent(context, ItemDetailActivity::class.java).apply {
            putExtra(ARG_ITEM_ID, itemId)
        }
    }
}