package com.development.propertiesapp.model

import com.google.gson.annotations.SerializedName

data class PropertyListing (

    @SerializedName("Id")
    val id: String = "",

    @SerializedName("Description")
    val description: String = "",

    @SerializedName("Bathrooms")
    val bathrooms: Int = 0,

    @SerializedName("Bedrooms")
    val bedrooms: Int = 0,

    @SerializedName("Carspaces")
    val carSpaces: Int = 0,

    @SerializedName("Location")
    val location: PropertyLocation,

    @SerializedName("owner")
    val owner: PropertyOwner,

    @SerializedName("ImageUrls")
    val imageUrls: List<String>
)