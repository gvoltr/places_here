package com.gvoltr.placeshere.presentation.places.view


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gvoltr.placeshere.R
import com.gvoltr.placeshere.presentation.places.viewmodel.PlaceCategoryItem
import kotlinx.android.synthetic.main.list_item_place_category.view.*

class PlaceCategoriesAdapter(
    private val itemSelected: (PlaceCategoryItem) -> Unit,
    private val itemUnselected: (PlaceCategoryItem) -> Unit
) : ListAdapter<PlaceCategoryItem, PlaceCategoriesAdapter.PlaceCategoryViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceCategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_place_category, parent, false)
        return PlaceCategoryViewHolder(view, itemSelected, itemUnselected)
    }

    override fun onBindViewHolder(holder: PlaceCategoryViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    class PlaceCategoryViewHolder(
        itemView: View,
        itemSelected: (PlaceCategoryItem) -> Unit,
        itemUnselected: (PlaceCategoryItem) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        lateinit var placeCategoryItem: PlaceCategoryItem

        init {
            itemView.checkbox.setOnClickListener {
                if ((it as CheckBox).isChecked) {
                    itemSelected.invoke(placeCategoryItem)
                } else {
                    itemUnselected.invoke(placeCategoryItem)
                }
            }
        }

        fun bindData(placeCategory: PlaceCategoryItem) {
            this.placeCategoryItem = placeCategory
            itemView.checkbox.text = placeCategory.placeCategory.title
            itemView.checkbox.isChecked = placeCategory.checked
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<PlaceCategoryItem>() {
        override fun areItemsTheSame(
            oldItem: PlaceCategoryItem,
            newItem: PlaceCategoryItem
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: PlaceCategoryItem,
            newItem: PlaceCategoryItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}