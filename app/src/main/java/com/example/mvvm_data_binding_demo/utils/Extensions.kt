package com.example.mvvm_data_binding_demo.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("srcUrl")
fun ImageView.bindSrcUrl(
    url: String,
) = Glide.with(this).load(url).into(this)