package com.development.propertiesapp.model

import com.google.gson.annotations.SerializedName

data class PropertyOwner(

    @SerializedName("name")
    val name: String = "",

    @SerializedName("lastName")
    val lastName: String = "",

    @SerializedName("dob")
    val dateOfBirth: String = "",

    @SerializedName("image")
    val image: PropertyOwnerImage
)