package com.android.modular_m_vm.dagger2.modules

import com.android.modular_m_vm.api.Api
import com.android.modular_m_vm.controllers.IPeopleController
import com.android.modular_m_vm.controllers.PeopleController
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiControllerModule {
    @Singleton
    @Provides
    fun providesPeopleController(api: Api): IPeopleController = PeopleController(api)
}