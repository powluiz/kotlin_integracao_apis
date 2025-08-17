package com.example.integracaokotlin.ui

import android.widget.ImageView
import com.example.integracaokotlin.R
import com.squareup.picasso.Picasso

fun ImageView.loadUrl(imageUrl: String) {
    Picasso.get()
        .load(imageUrl)
        .transform(CircleTransform())
        .placeholder(R.drawable.ic_image_placeholder)
        .error(R.drawable.ic_image_error)
        .into(this)

}