package com.example.integracaokotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.integracaokotlin.R
import com.example.integracaokotlin.model.Item
import com.example.integracaokotlin.ui.loadUrl

class ItemAdapter(
    private val items: List<Item>,
    private val itemClickListener: (Item) -> Unit
): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.name.text = item.value.name
        holder.age.text = holder.itemView.context .getString(R.string.item_age, item.value.age.toString())
        holder.address.text = item.value.location.name
        holder.imageView.loadUrl(item.value.imageUrl)
        holder.itemView.setOnClickListener {
            itemClickListener(item)
        }

    }

    override fun getItemCount(): Int = items.size

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.image) //item_layout --> @+id/image
        val name: TextView = view.findViewById(R.id.name) //item_layout --> @+id/name
        val age: TextView = view.findViewById(R.id.age) //item_layout --> @+id/age
        val address: TextView = view.findViewById(R.id.address) //item_layout --> @+id/address
    }
}