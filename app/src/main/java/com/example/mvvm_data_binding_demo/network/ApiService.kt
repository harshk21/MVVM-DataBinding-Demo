package com.example.mvvm_data_binding_demo.network

import com.example.mvvm_data_binding_demo.data.ProductsItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("products")
    fun getUsers(): Call<ArrayList<ProductsItem>>

}