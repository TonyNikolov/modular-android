package com.android.modular_m_vm.dagger2.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.modular_m_vm.viewmodels.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import net.kidjo.books.app.books_m_vm.dagger2.viewmodels.ViewModelFactory
import net.kidjo.books.app.books_m_vm.dagger2.viewmodels.ViewModelKey


@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel
}