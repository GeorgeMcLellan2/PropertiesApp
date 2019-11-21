package com.development.propertiesapp.model

import com.google.gson.annotations.SerializedName

data class PropertyOwnerImage(

    @SerializedName("big.url")
    val big: String,

    @SerializedName("small.url")
    val small: String,

    @SerializedName("medium.url")
    val medium: String
)