package com.lucascampos.testing.data

import com.lucascampos.testing.data.model.Repository
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("")
    fun listRepositories(): Call<Repository>

}