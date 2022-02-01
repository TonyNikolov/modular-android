package com.android.modular_m_vm.controllers

import com.android.modular_m_vm.models.responses.ApiResponse
import com.android.modular_m_vm.models.responses.PeopleResponse

interface IPeopleController {
    suspend fun getPeople(): ApiResponse<PeopleResponse>
}