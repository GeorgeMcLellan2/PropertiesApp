package com.development.propertiesapp.presentation

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.development.propertiesapp.R
import com.development.propertiesapp.adapters.ViewPropertiesRecyclerViewAdapter
import com.development.propertiesapp.model.PropertyListing

class ViewPropertiesActivity : Activity() {

    private var propertiesRecyclerView: RecyclerView? = null

    private val propertiesAdapter: ViewPropertiesRecyclerViewAdapter by lazy {
        ViewPropertiesRecyclerViewAdapter(context = this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_properties)

        propertiesRecyclerView = findViewById(R.id.view_properties_recycler_view)

        //fake data temporarily
        propertiesAdapter.setPropertyListings(listOf(PropertyListing(id = "#@432432"), PropertyListing(id = "helllooo")))

        propertiesRecyclerView?.apply {
            adapter = propertiesAdapter
            layoutManager = GridLayoutManager(context, VIEW_PROPERTIES_RECYCLER_VIEW_COLUMNS)
        }
    }

    companion object {
        const val VIEW_PROPERTIES_RECYCLER_VIEW_COLUMNS = 2
    }
}