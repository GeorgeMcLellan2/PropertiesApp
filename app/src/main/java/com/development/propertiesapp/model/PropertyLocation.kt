package com.development.propertiesapp.model

import com.google.gson.annotations.SerializedName

data class PropertyLocation(
    @SerializedName("Address")
    val address: String,

    @SerializedName("Address2")
    val address2: String,

    @SerializedName("State")
    val state: String,

    @SerializedName("Suburb")
    val suburb: String
)