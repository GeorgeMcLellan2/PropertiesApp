package com.development.propertiesapp.presentation.viewProperties

import android.view.View
import com.development.propertiesapp.model.PropertyListing

interface PropertySelectedListener {
    fun onPropertySelected(propertyListing: PropertyListing, imageView: View)
}