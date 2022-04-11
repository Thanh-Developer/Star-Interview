package com.interview.star.utils

import android.graphics.drawable.Drawable
import android.webkit.WebView
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder

/**
 *  Handle load image by binding
 */
@BindingAdapter("loadImage")
fun ImageView.loadImage(url: String?) {
    val requestBuilder: RequestBuilder<Drawable> = Glide.with(context).load(url)
    requestBuilder.into(this)
}

/**
 *  Handle load web view by binding
 */
@BindingAdapter("loadWebView")
fun WebView.updateUrl(url: String?) {
    url?.let {
        loadUrl(url)
    }
}