package com.android.modular_views_mobile

import com.android.modular_m_vm.dagger2.modules.ApiControllerModule
import com.android.modular_m_vm.dagger2.modules.NetworkModule
import com.android.modular_m_vm.dagger2.modules.ViewModelModule
import com.android.modular_views_mobile.ui.base.BaseFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ViewModelModule::class, ApiControllerModule::class])
interface ModularAppComponent {
    fun inject(baseFragment: BaseFragment)
}