package com.gvoltr.placeshere.presentation.address.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gvoltr.placeshere.data.entity.address.Address
import com.gvoltr.placeshere.domain.AddressInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class AddressViewModel(
    private val addressInteractor: AddressInteractor
) : ViewModel() {

    private val LOG_TAG = "AddressViewModel"
    private var subscription: Disposable? = null
    private val addressLiveData = MutableLiveData<Address>()

    init {
        subscribeToAddressChanges()
    }

    override fun onCleared() {
        subscription?.dispose()
        super.onCleared()
    }

    fun getAddressLiveData() : LiveData<Address> = addressLiveData

    private fun subscribeToAddressChanges() {
        subscription = addressInteractor.getAddressChangesStream()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    addressLiveData.value = it
                },
                {
                    Log.e(LOG_TAG, "Retrieving address error")
                    it.printStackTrace()
                })
    }
}