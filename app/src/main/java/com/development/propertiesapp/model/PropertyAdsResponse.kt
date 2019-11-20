package com.development.propertiesapp.model

import com.google.gson.annotations.SerializedName

data class PropertyAdsResponse<T>(

    @SerializedName("ad_id")
    val adId: String = "",


    /**
     * Template type for re-use
     * In case in the future a similar endpoint is created
     * with adId, title and a different data type for data
     */
    @SerializedName("data")
    val data: T,

    @SerializedName("title")
    val title: String = ""

)