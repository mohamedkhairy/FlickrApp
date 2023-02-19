package com.example.presentation.utils


import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions



fun ImageView.loadAsyncImage(url: String?, isDecode: Boolean = true) {
    Glide.with(this.context).load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .apply(
            RequestOptions()
                .format(if (isDecode) DecodeFormat.PREFER_RGB_565 else DecodeFormat.PREFER_ARGB_8888)
        )
//        .centerCrop()
        .transition(GenericTransitionOptions.with<Drawable>(android.R.anim.fade_in)).into(this)

}


fun View.hideView() {
    this.visibility = View.GONE
}

fun View.showView() {
    this.visibility = View.VISIBLE
}

enum class DisplayImageSource{
    HOME,
    FAVORITES
}