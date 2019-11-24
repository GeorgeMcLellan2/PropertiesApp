package com.development.propertiesapp.presentation.viewPropertyDetails

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.development.propertiesapp.R
import com.development.propertiesapp.databinding.ActivityViewPropertyDetailsBinding
import com.development.propertiesapp.model.PropertyListing

class ViewPropertyDetailsActivity : AppCompatActivity() {

    lateinit var propertyListing: PropertyListing
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent.hasExtra("propertyListing")) {
            propertyListing = intent.getParcelableExtra("propertyListing")!!
            Log.d(TAG, "onCreate: Property $propertyListing")
            val binding = DataBindingUtil.setContentView<ActivityViewPropertyDetailsBinding>(this, R.layout.activity_view_property_details)
            binding.property = propertyListing
        } else{
            Log.e(TAG, "Did not receive propertyListing intent extra")
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        private const val TAG = "ViewPropertyDetails"
    }
}