package com.gvoltr.placeshere.presentation.address.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gvoltr.placeshere.data.entity.address.Address
import com.gvoltr.placeshere.domain.address.AddressInteractor
import com.gvoltr.placeshere.presentation.utils.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class AddressViewModel(
    private val addressInteractor: AddressInteractor
) : ViewModel() {

    private val LOG_TAG = "AddressViewModel"
    private var subscription: Disposable? = null
    private val addressLiveData = MutableLiveData<Address>()
    private val exceptionsLiveData = MutableLiveData<Event<Throwable>>()

    init {
        subscribeToAddressChanges()
    }

    override fun onCleared() {
        subscription?.dispose()
        super.onCleared()
    }

    fun getAddressLiveData(): LiveData<Address> = addressLiveData

    fun getExceptionsLiveData(): LiveData<Event<Throwable>> = exceptionsLiveData

    private fun subscribeToAddressChanges() {
        subscription = addressInteractor.getAddressChangesStream()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (it.value != null) {
                        addressLiveData.value = it.value
                    } else {
                        it.error?.let { error ->
                            exceptionsLiveData.value = Event(error)
                        }
                    }
                },
                {
                    Log.e(LOG_TAG, "Retrieving address error")
                    it.printStackTrace()
                })
    }
}