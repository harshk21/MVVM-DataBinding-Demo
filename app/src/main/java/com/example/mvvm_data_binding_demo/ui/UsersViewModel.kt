package com.example.mvvm_data_binding_demo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_data_binding_demo.application.MyApplication
import com.example.mvvm_data_binding_demo.data.Users
import com.example.mvvm_data_binding_demo.repository.UsersRepo
import com.example.mvvm_data_binding_demo.viewmodel.BaseViewModel

class UsersViewModel : BaseViewModel() {

    var userDataResponse = MutableLiveData<List<Users>>()
    var itemPerUser = MutableLiveData<Users>()
    private val dao = MyApplication.roomDatabase?.userDao()

    fun getUsers() {
        isLoading.value = true
        UsersRepo.getUsersList(
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

    fun setSingleUser(item: Users) {
        isLoading.value = true
        itemPerUser.value = item
    }

    fun getUsersDataBase(): LiveData<List<Users>>? {
        return dao?.getAll()
    }

}
