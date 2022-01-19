package com.example.mvvm_data_binding_demo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    var isLoading = MutableLiveData<Boolean>()
    val onFailureResponse = MutableLiveData<Throwable>()
}