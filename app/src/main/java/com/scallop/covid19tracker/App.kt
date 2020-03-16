package com.scallop.covid19tracker

import android.app.Application
import com.scallop.covid19tracker.di.*
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        loadKoin()
    }

    private fun loadKoin() {
        startKoin(
            this,
            listOf(
                mNetworkModules,
                mViewModels,
                mRepositoryModules,
                mUseCaseModules,
                mLocalModules
            )

        )
    }
}