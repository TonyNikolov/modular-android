package com.android.modular_views_mobile.ui.base

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.modular_views_mobile.ModularApp
import com.android.modular_views_mobile.ModularAppComponent
import javax.inject.Inject

abstract class BaseFragment : Fragment() {
    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    private val appComponent: ModularAppComponent
        get() {
            val activity: Activity? = activity
            if (activity != null) {
                val app = activity.application
                if (app is ModularApp) {
                    return app.appComponent
                }
            }
            throw RuntimeException("Could not locate AppComponent..")
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        appComponent.inject(this)
    }
}