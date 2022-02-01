package com.android.modular_m_vm.controllers

import com.android.modular_m_vm.api.Api
import com.android.modular_m_vm.models.responses.ApiResponse
import com.android.modular_m_vm.models.responses.PeopleResponse

class PeopleController(private val api: Api) : IPeopleController {
    override suspend fun getPeople(): ApiResponse<PeopleResponse> {
        return ApiResponse(api.getPeople())
    }
}