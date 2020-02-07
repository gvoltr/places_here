package com.gvoltr.placeshere.presentation.address.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.gvoltr.placeshere.R
import com.gvoltr.placeshere.data.entity.address.Address
import com.gvoltr.placeshere.presentation.address.viewmodel.AddressViewModel
import kotlinx.android.synthetic.main.fragment_address.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddressFragment : Fragment() {

    private val addressViewModel: AddressViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_address, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToVM()
    }

    private fun subscribeToVM() {
        addressViewModel.getAddressLiveData().observe(viewLifecycleOwner, Observer {
            displayAddress(it)
        })
        addressViewModel.getExceptionsLiveData().observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(
                    context,
                    "Oops! We got an error ${it.localizedMessage}",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    private fun displayAddress(address: Address) {
        locationText.text = getString(
            R.string.label_location_details,
            address.displayPosition.latitude,
            address.displayPosition.longitude
        )

        addressText.text = getString(R.string.label_address_details, address.fullDescription)
    }
}