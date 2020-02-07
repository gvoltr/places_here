package com.gvoltr.placeshere.presentation.places.view

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gvoltr.placeshere.R
import com.gvoltr.placeshere.data.entity.place.Place
import kotlinx.android.synthetic.main.list_item_place.view.*

class PlaceAdapter(
    private val placeClicked: (Place) -> Unit
) : ListAdapter<Place, PlaceAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_place, parent, false)
        return ViewHolder(view, placeClicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    class ViewHolder(
        itemView: View,
        placeClicked: (Place) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private lateinit var place: Place

        init {
            itemView.setOnClickListener {
                placeClicked.invoke(place)
            }
        }

        fun bindData(place: Place) {
            this.place = place
            itemView.title.text = place.title
            itemView.category.text = place.categoryTitle
            itemView.vicinity.text = Html.fromHtml(place.vicinity)
            itemView.location.text = itemView.resources.getString(
                R.string.label_location_details,
                place.location.latitude,
                place.location.longitude
            )
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Place>() {
        override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem == newItem
        }
    }
}