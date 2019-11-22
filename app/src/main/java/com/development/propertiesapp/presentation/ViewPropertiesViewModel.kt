package com.development.propertiesapp.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.development.propertiesapp.model.PropertyListing
import com.development.propertiesapp.network.PropertiesApi
import com.development.propertiesapp.network.RetrofitClient
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ViewPropertiesViewModel : ViewModel() {

    //TODO replace with dagger injection
    val api = RetrofitClient.retrofitInstance.create(PropertiesApi::class.java)

    /**
     * For observing when property listing data is loaded
     */
    var propertyListingsAreLoading = MutableLiveData<Boolean>(false)

    /**
     * To prevent re-calling the api to retrieve on orientation change, store in a variable
     * that is persisted in the ViewModel on orientation change
     */
    private var storedPropertyListings = emptyList<PropertyListing>()

    fun getPropertyListings(): Single<List<PropertyListing>> {
        propertyListingsAreLoading.value = true
        return if (storedPropertyListings.isEmpty()) {
            Log.d(TAG, "getPropertyListings: retrieving property listings from server...")
            api.getPropertyListings()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //Return the PropertyListings without the additional unnecessary data
                .map { it.data.propertyListings }
                .doOnSuccess {
                    //Store the listings retrieved by this call
                    storedPropertyListings = it
                }
                .doFinally { propertyListingsAreLoading.value = false }
        } else {
            //If we already have the listings from a previous api call, serve them to the consumer
            Log.d(TAG, "getPropertyListings: returning stored property listings")
            Single.just(storedPropertyListings)
                .doFinally { propertyListingsAreLoading.value = false }
        }
    }

    companion object {
        private const val TAG = "ViewPropertiesViewModel"
    }
}