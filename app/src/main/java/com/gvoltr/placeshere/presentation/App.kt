package com.gvoltr.placeshere.presentation

import android.app.Application
import com.gvoltr.placeshere.data.di.dataModules
import com.gvoltr.placeshere.domain.di.domainModule
import com.gvoltr.placeshere.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        setupDIGraph()
    }

    private fun setupDIGraph() {
        startKoin {
            androidContext(this@App)
            modules(listOf(*dataModules, domainModule, presentationModule))
        }
    }

}