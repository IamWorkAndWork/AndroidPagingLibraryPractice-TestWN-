package com.example.testwn.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.testwn.R


fun ImageView.loadImageWithGlide(imgPath: String?) {
//    Log.d("print", "load image = " + imgPath)
    Glide.with(this)
        .load(R.drawable.bitcoin) // image url
        .placeholder(R.mipmap.ic_launcher)
        .error(R.mipmap.ic_launcher)
        .fitCenter()
//        .override(200, 200); // resizing
        .centerCrop()
        .into(this)
}



