package com.android.modular_m_vm.api

import com.android.modular_m_vm.models.responses.PeopleResponse
import retrofit2.Call
import retrofit2.http.*

const val URL_API_BASE = "https://swapi.dev/api/"

interface Api {
    @GET("people")
    fun getPeople(): Call<PeopleResponse>
}

