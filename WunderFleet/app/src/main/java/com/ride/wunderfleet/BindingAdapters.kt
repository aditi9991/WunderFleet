package com.ride.wunderfleet

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("image_url")
fun loadImage(view: ImageView, photoUrl: String?) {
    Glide.with(AppApplicationClass.context).load(photoUrl).into(view)
}