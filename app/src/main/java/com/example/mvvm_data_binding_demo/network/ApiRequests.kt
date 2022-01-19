package com.example.mvvm_data_binding_demo.network

import com.example.mvvm_data_binding_demo.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiRequests {

    private var retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL).client(clientRequest())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun clientRequest(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor = interceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    fun createService(): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
