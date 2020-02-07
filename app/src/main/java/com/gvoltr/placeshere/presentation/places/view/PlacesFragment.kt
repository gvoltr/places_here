package com.gvoltr.placeshere.presentation.places.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.tabs.TabLayout
import com.gvoltr.placeshere.R
import com.gvoltr.placeshere.data.entity.location.Location
import com.gvoltr.placeshere.data.entity.place.Place
import com.gvoltr.placeshere.presentation.places.viewmodel.PlacesViewModel
import com.gvoltr.placeshere.presentation.places.viewmodel.ViewMode
import com.gvoltr.placeshere.presentation.utils.toLatLng
import kotlinx.android.synthetic.main.fragment_places.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlacesFragment : Fragment(), OnMapReadyCallback {

    private val placesViewModel: PlacesViewModel by viewModel()
    private lateinit var categoriesAdapter: PlaceCategoriesAdapter
    private lateinit var placesAdapter: PlaceAdapter
    private var map: GoogleMap? = null

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

    override fun onMapReady(map: GoogleMap?) {
        this.map = map
        setupMap()
    }

    private fun setupView() {
        requestMap()
        setupCategoriesList()
        setupPlacesList()
        setupTabs()
    }

    private fun setupCategoriesList() {
        categoriesList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        //Adapter initialization with selected and unselected callbacks
        categoriesAdapter = PlaceCategoriesAdapter(itemSelected = {
            placesViewModel.categorySelected(it)
        }, itemUnselected = {
            placesViewModel.categoryUnselected(it)
        })

        categoriesList.adapter = categoriesAdapter
    }

    private fun setupPlacesList() {
        placesList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        placesAdapter = PlaceAdapter(placeClicked = {

        })
        placesList.adapter = placesAdapter
    }

    private fun setupTabs() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab) {}

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> placesViewModel.mapModeSelected()
                    1 -> placesViewModel.listModeSelected()
                }
            }
        })
    }

    private fun requestMap() {
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun subscribeToVM() {
        placesViewModel.getPlaceCategoriesLiveData().observe(viewLifecycleOwner, Observer {
            categoriesAdapter.submitList(it)
        })
        placesViewModel.getPlacesLiveData().observe(viewLifecycleOwner, Observer {
            placesAdapter.submitList(it)
            placesAdapter.notifyDataSetChanged()
            drawPlacesOnMap(it)
        })
        placesViewModel.getCurrentLocationLiveData().observe(viewLifecycleOwner, Observer {
            moveMapToLocation(it)
        })
        placesViewModel.getViewModeLiveData().observe(viewLifecycleOwner, Observer {
            displayViewMode(it)
        })
    }

    private fun setupMap() {
        map?.uiSettings?.isZoomControlsEnabled = true
        map?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style))

        placesViewModel.getCurrentLocationLiveData().value?.let {
            moveMapToLocation(it)
        }

        placesViewModel.getPlacesLiveData().value?.let {
            drawPlacesOnMap(it)
        }
    }

    private fun moveMapToLocation(location: Location) {
        // initialize my location when we are sure there is location permission granted
        map?.isMyLocationEnabled = true
        map?.animateCamera(CameraUpdateFactory.newLatLngZoom(location.toLatLng(), 15f))
    }

    private fun drawPlacesOnMap(places: List<Place>) {
        map?.let { map ->
            map.clear()
            places.forEach {
                map.addMarker(
                    MarkerOptions().position(it.location.toLatLng()).title(it.title)
                )
            }
        }
    }

    private fun displayViewMode(viewMode: ViewMode) {
        when (viewMode) {
            is ViewMode.MapMode -> {
                mapFrameLayout.visibility = View.VISIBLE
                placesList.visibility = View.GONE
                tabLayout.selectTab(tabLayout.getTabAt(0))
            }
            is ViewMode.ListMode -> {
                mapFrameLayout.visibility = View.GONE
                placesList.visibility = View.VISIBLE
                tabLayout.selectTab(tabLayout.getTabAt(1))
            }
        }
    }
}