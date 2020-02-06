package com.gvoltr.placeshere.presentation.places.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gvoltr.placeshere.R
import com.gvoltr.placeshere.presentation.places.viewmodel.PlacesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlacesFragment : Fragment() {

    private val placesViewModel : PlacesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_places, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        placesViewModel.toString()
    }

}