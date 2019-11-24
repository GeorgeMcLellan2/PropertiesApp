package com.development.propertiesapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PropertyOwner(

    @SerializedName("name")
    val name: String = "",

    @SerializedName("lastName")
    val lastName: String = "",

    @SerializedName("dob")
    val dateOfBirth: String = "",

    @SerializedName("image")
    val image: PropertyOwnerImage
): Parcelable