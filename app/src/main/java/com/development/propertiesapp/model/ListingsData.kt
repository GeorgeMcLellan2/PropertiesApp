package com.development.propertiesapp.model

import com.google.gson.annotations.SerializedName

data class ListingsData(

    @SerializedName("listings")
    val propertyListings: List<PropertyListing>

)