package com.gvoltr.placeshere.presentation.places.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gvoltr.placeshere.R
import com.gvoltr.placeshere.presentation.places.viewmodel.PlacesViewModel
import kotlinx.android.synthetic.main.fragment_places.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class PlacesFragment : Fragment() {

    private val placesViewModel: PlacesViewModel by viewModel()
    private lateinit var categoriesAdapter: PlaceCategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_places, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        subscribeToVM()
    }

    private fun setupView() {
        setupCategoriesList()
    }

    private fun setupCategoriesList() {
        categoriesList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        categoriesAdapter = PlaceCategoriesAdapter(itemSelected = {
            placesViewModel.categorySelected(it)
        }, itemUnselected = {
            placesViewModel.categoryUnselected(it)
        })
        categoriesList.adapter = categoriesAdapter
    }

    private fun subscribeToVM() {
        placesViewModel.getPlaceCategoriesLiveData().observe(viewLifecycleOwner, Observer {
            categoriesAdapter.submitList(it)
        })
    }

}