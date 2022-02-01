package com.android.modular_views_mobile

import android.app.Application
import com.android.modular_m_vm.dagger2.modules.NetworkModule
import timber.log.Timber

class ModularApp : Application() {
    lateinit var appComponent: ModularAppComponent

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerModularAppComponent.builder()
            .networkModule(NetworkModule(this))
            .build()
    }
}