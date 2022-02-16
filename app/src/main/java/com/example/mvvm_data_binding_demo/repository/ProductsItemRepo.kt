package com.example.mvvm_data_binding_demo.repository

import com.example.mvvm_data_binding_demo.data.ProductsItem
import com.example.mvvm_data_binding_demo.network.ApiRequests
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object ProductsItemRepo {
    private val apiService = ApiRequests.createService()


    fun getProductsItemList(

        success: (ArrayList<ProductsItem>) -> Unit, failure: (Throwable) -> Unit
    ) {
        apiService.getUsers().enqueue(object : Callback<ArrayList<ProductsItem>> {
            override fun onResponse(
                call: Call<ArrayList<ProductsItem>>,
                response: Response<ArrayList<ProductsItem>>
            ) {
                response.body()?.let {
                    success(it)
                }
            }

            override fun onFailure(call: Call<ArrayList<ProductsItem>>, t: Throwable) {
                failure(t)
            }
        })
    }


}