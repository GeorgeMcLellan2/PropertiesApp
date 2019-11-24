package com.development.propertiesapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PropertyOwnerImage(

    @SerializedName("big")
    val big: ImageUrl,

    @SerializedName("small")
    val small: ImageUrl,

    @SerializedName("medium")
    val medium: ImageUrl
): Parcelable

@Parcelize
data class ImageUrl(
    @SerializedName("url")
    val imageUrl: String
): Parcelable