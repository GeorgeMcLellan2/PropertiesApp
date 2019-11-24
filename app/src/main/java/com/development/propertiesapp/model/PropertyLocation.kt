package com.development.propertiesapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PropertyLocation(
    @SerializedName("Address")
    val address: String,

    @SerializedName("Address2")
    val address2: String,

    @SerializedName("State")
    val state: String,

    @SerializedName("Suburb")
    val suburb: String
): Parcelable