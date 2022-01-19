package com.example.mvvm_data_binding_demo.network

import com.example.mvvm_data_binding_demo.data.Users
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    fun getUsers(): Call<List<Users>>

}