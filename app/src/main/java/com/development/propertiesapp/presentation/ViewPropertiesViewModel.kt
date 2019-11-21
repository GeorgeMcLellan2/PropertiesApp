package com.development.propertiesapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.development.propertiesapp.model.PropertyListing
import com.development.propertiesapp.network.PropertiesApi
import com.development.propertiesapp.network.RetrofitClient
import io.reactivex.Single

class ViewPropertiesViewModel : ViewModel() {
    var hi = false
    val api = RetrofitClient.retrofitInstance.create(PropertiesApi::class.java)

    /**
     * To prevent re-calling the api to retrieve on orientation change, store in a variable
     * that is persisted in the ViewModel on orientation change
     */
    private var storedPropertyListings = emptyList<PropertyListing>()

    fun getPropertyListings(): Single<List<PropertyListing>> {
        return if (storedPropertyListings.isEmpty()) {
            Log.d(TAG, "getPropertyListings: retrieving property listings from server...")
            api.getPropertyListings()
                //Return the PropertyListings without the additional unnecessary data
                .map { it.data.propertyListings }
                .doOnSuccess {
                    //Store the listings retrieved by this call
                    storedPropertyListings = it
                }
        } else {
            //If we already have the listings from a previous api call, serve them to the consumer
            Log.d(TAG, "getPropertyListings: returning stored property listings")
            Single.just(storedPropertyListings)
        }
    }

    companion object {
        private const val TAG = "ViewPropertiesViewModel"
    }
}