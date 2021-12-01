package com.test.bookmyseat.utils

import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.test.bookmyseat.R

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("android:setImage")
    fun setImage(imageView: ImageView, imageUrl: String?) {
        if (!TextUtils.isEmpty(imageUrl))
            Glide.with(imageView.context).load("https://image.tmdb.org/t/p/w780/$imageUrl").into(imageView)
        else
            imageView.setImageResource(R.drawable.product_placeholder)
    }
}