package com.example.mvvm_data_binding_demo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_data_binding_demo.application.MyApplication
import com.example.mvvm_data_binding_demo.data.ProductsItem
import com.example.mvvm_data_binding_demo.repository.ProductsItemRepo
import com.example.mvvm_data_binding_demo.viewmodel.BaseViewModel

class UsersViewModel : BaseViewModel() {

    var userDataResponse = MutableLiveData<ArrayList<ProductsItem>>()
    var itemPerUser = MutableLiveData<ProductsItem>()
    private val dao = MyApplication.roomDatabase?.userDao()

    fun getUsers() {
        isLoading.value = true
        ProductsItemRepo.getProductsItemList(
            success = {
                isLoading.value = false
                userDataResponse.value = it
                it.forEach { users ->
                    dao?.insertAll(users)
                }
            },
            failure = {
                isLoading.value = false
                onFailureResponse.value = it
            })
    }

    fun setSingleUser(item: ProductsItem) {
        isLoading.value = true
        itemPerUser.value = item
    }

    fun getUsersDataBase(): LiveData<List<ProductsItem>>? {
        return dao?.getAll()
    }

}
