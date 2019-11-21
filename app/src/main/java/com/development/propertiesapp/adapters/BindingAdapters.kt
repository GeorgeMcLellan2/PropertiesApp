package com.development.propertiesapp.adapters

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

/**
 * For data binding to provide additional useful attributes on views
 */
object BindingAdapters {

    /**
     * Provides the imageUrl attribute on view using data binding
     * which will load the image using Picasso and display it in the view.
     * This reduces Activity/Fragment/Adapter boilerplate substantially
     */
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadItemImage(view: ImageView, imageUrls: List<String>) {

        for (imageUrl in imageUrls) {
            Log.d("BindingAdapter", "loadItemImage: $imageUrl")

            //Temporary fix for bad image url's ending with .html
            if (imageUrl.isNotEmpty() && (
                        imageUrl.endsWith(".jpg")
                                ||
                        imageUrl.endsWith(".png")))
                {
                Picasso.with(view.context).load(imageUrl)
                    .into(view)
                break
            }
        }

    }
}