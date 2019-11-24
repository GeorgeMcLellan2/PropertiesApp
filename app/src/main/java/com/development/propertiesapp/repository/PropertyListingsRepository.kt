package com.development.propertiesapp.repository

import com.development.propertiesapp.model.PropertyListing
import com.development.propertiesapp.network.PropertiesApi
import com.development.propertiesapp.network.RetrofitClient
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Responsible for serving PropertyListing data
 */
class PropertyListingsRepository {

    //TODO replace with dagger injection
    private val api = RetrofitClient.retrofitInstance.create(PropertiesApi::class.java)

    fun getPropertyListings(): Single<List<PropertyListing>> {
       return api.getPropertyListings()
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
           //Return the PropertyListings without the additional unnecessary data
           .map { it.data.propertyListings }
   }
}