package com.development.propertiesapp.network

import com.development.propertiesapp.model.ListingsData
import com.development.propertiesapp.model.PropertyAdsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface PropertiesApi {
    @GET("test/properties")
    fun getPropertyListings(): Single<PropertyAdsResponse<ListingsData>>
}