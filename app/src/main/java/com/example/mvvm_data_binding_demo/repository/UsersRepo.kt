package com.example.mvvm_data_binding_demo.repository

import com.example.mvvm_data_binding_demo.data.Users
import com.example.mvvm_data_binding_demo.network.ApiRequests
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object UsersRepo {
    private val apiService = ApiRequests.createService()


    fun getUsersList(

        success: (List<Users>) -> Unit, failure: (Throwable) -> Unit
    ) {
        apiService.getUsers().enqueue(object : Callback<List<Users>> {
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                response.body()?.let {
                    success(it)
                }
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                failure(t)
            }
        })
    }


}