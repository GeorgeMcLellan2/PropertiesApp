package com.development.propertiesapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.development.propertiesapp.databinding.RecyclerItemPropertyListingBinding
import com.development.propertiesapp.model.PropertyListing

class ViewPropertiesRecyclerViewAdapter(
    private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var propertyListings: List<PropertyListing> = emptyList()

    /**
     * Represents a PropertyListing with information about the property
     */
    class PropertyViewHolder(private val binding: RecyclerItemPropertyListingBinding): RecyclerView.ViewHolder(binding.root) {
        /**
         * For populating the binding with the relevant property data
         */
        fun bind(property: PropertyListing) {
            binding.property = property
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //Create the binding for the property listing
        return PropertyViewHolder(RecyclerItemPropertyListingBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int = propertyListings.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PropertyViewHolder -> {
                val property = propertyListings[position]
                holder.bind(property)
            }
        }
    }

    /**
     * Used to populate the recycler view data after this adapter has been initialised
     */
    fun setPropertyListings(propertyListings: List<PropertyListing>) {
        this.propertyListings = propertyListings
        notifyDataSetChanged()
    }

}