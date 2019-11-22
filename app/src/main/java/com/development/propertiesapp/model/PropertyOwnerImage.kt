package com.development.propertiesapp.model

import com.google.gson.annotations.SerializedName

data class PropertyOwnerImage(

    @SerializedName("big")
    val big: ImageUrl,

    @SerializedName("small")
    val small: ImageUrl,

    @SerializedName("medium")
    val medium: ImageUrl
)

data class ImageUrl(
    @SerializedName("url")
    val imageUrl: String
)