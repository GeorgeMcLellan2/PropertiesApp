package com.development.propertiesapp.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.development.propertiesapp.R
import com.development.propertiesapp.adapters.ViewPropertiesRecyclerViewAdapter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ViewPropertiesActivity : AppCompatActivity() {

    private var mPropertiesRecyclerView: RecyclerView? = null
    private var mProgressBar: ProgressBar? = null

    private val mCompositeDisposable = CompositeDisposable()

    private val mPropertiesAdapter: ViewPropertiesRecyclerViewAdapter by lazy {
        ViewPropertiesRecyclerViewAdapter(context = this)
    }

    private lateinit var mViewModel: ViewPropertiesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_properties)

        mViewModel = ViewModelProviders.of(this)[ViewPropertiesViewModel::class.java]

        //Todo Replace with ViewBinding when released in Android Studio 3.6
        //Using synthetics can be risky. No need for kotterknife either
        mPropertiesRecyclerView = findViewById(R.id.view_properties_recycler_view)
        mProgressBar = findViewById(R.id.view_properties_progress_bar)

        mPropertiesRecyclerView?.apply {
            adapter = mPropertiesAdapter
            layoutManager = LinearLayoutManager(context)
        }

        retrievePropertyListings()
        observeLoadingStatus()

    }

    private fun retrievePropertyListings() {
        mCompositeDisposable.add(mViewModel.getPropertyListings()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                //onSuccess
                { propertyListings ->
                    Log.d(TAG, "onCreate: setting property listings $propertyListings")
                    mPropertiesAdapter.setPropertyListings(propertyListings)
                },
                //onError
                { error ->
                    error.printStackTrace()

                }
            ))
    }

    private fun observeLoadingStatus() {
        //Update progress bar when finished loading property listings
        mViewModel.propertyListingsAreLoading.observe(
            this,
            Observer<Boolean> { isLoading ->
                mProgressBar?.visibility = if (isLoading) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            })
    }

    override fun onDestroy() {
        mCompositeDisposable.clear()
        super.onDestroy()
    }

    companion object {
        private const val TAG = "ViewPropertiesActivity"
    }
}