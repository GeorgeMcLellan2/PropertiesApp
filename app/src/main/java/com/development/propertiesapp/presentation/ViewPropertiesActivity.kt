package com.development.propertiesapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.development.propertiesapp.R
import com.development.propertiesapp.adapters.ViewPropertiesRecyclerViewAdapter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ViewPropertiesActivity : AppCompatActivity() {

    private var propertiesRecyclerView: RecyclerView? = null

    private val compositeDisposable = CompositeDisposable()

    private val propertiesAdapter: ViewPropertiesRecyclerViewAdapter by lazy {
        ViewPropertiesRecyclerViewAdapter(context = this)
    }

    lateinit var viewModel: ViewPropertiesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_properties)

        viewModel = ViewModelProviders.of(this)[ViewPropertiesViewModel::class.java]

        propertiesRecyclerView = findViewById(R.id.view_properties_recycler_view)

        propertiesRecyclerView?.apply {
            adapter = propertiesAdapter
            layoutManager = GridLayoutManager(context, VIEW_PROPERTIES_RECYCLER_VIEW_COLUMNS)
        }

        compositeDisposable.add(viewModel.getPropertyListings()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                //onSuccess
                { propertyListings ->
                    Log.d(TAG, "onCreate: ${viewModel.hi}")
                    viewModel.hi = true
                    Log.d(TAG, "onCreate: setting property listings")
                    propertiesAdapter.setPropertyListings(propertyListings)
                },
                //onError
                { error ->
                    error.printStackTrace()

                }
            ))
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