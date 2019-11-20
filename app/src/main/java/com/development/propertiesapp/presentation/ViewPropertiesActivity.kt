package com.development.propertiesapp.presentation

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.development.propertiesapp.R
import com.development.propertiesapp.adapters.ViewPropertiesRecyclerViewAdapter
import com.development.propertiesapp.model.ListingsData
import com.development.propertiesapp.model.PropertyAdsResponse
import com.development.propertiesapp.model.PropertyListing
import com.development.propertiesapp.network.PropertiesApi
import com.development.propertiesapp.network.RetrofitClient
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ViewPropertiesActivity : Activity() {

    private var propertiesRecyclerView: RecyclerView? = null

    private val compositeDisposable = CompositeDisposable()

    private val propertiesAdapter: ViewPropertiesRecyclerViewAdapter by lazy {
        ViewPropertiesRecyclerViewAdapter(context = this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_properties)
        propertiesRecyclerView = findViewById(R.id.view_properties_recycler_view)

        propertiesRecyclerView?.apply {
            adapter = propertiesAdapter
            layoutManager = GridLayoutManager(context, VIEW_PROPERTIES_RECYCLER_VIEW_COLUMNS)
        }

        val api = RetrofitClient.retrofitInstance.create(PropertiesApi::class.java)
        
        api.getPropertyListings()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: SingleObserver<PropertyAdsResponse<ListingsData>> {
                override fun onSuccess(res: PropertyAdsResponse<ListingsData>) {
                    Log.d(TAG, "onSuccess: $res")
                    Log.d(TAG, "onSuccess: listings: ${res.data.propertyListings.size}")
                    propertiesAdapter.setPropertyListings(res.data.propertyListings)
                }

                override fun onSubscribe(d: Disposable) {}

                override fun onError(e: Throwable) {
                    //Todo - handle error and inform user
                }

            })
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    companion object {
        private const val TAG = "ViewPropertiesActivity"
        const val VIEW_PROPERTIES_RECYCLER_VIEW_COLUMNS = 2
    }
}