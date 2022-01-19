package com.example.mvvm_data_binding_demo.ui


import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_data_binding_demo.R
import com.example.mvvm_data_binding_demo.adapters.UsersAdapter
import com.example.mvvm_data_binding_demo.data.Users
import com.example.mvvm_data_binding_demo.databinding.ActivityMainBinding


class UsersMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mViewModel: UsersViewModel
    private lateinit var usersList: ArrayList<Users>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mViewModel = ViewModelProvider(this)[UsersViewModel::class.java]
        usersList = ArrayList()
        binding.myViewModel = mViewModel
        mViewModel.getUsers()
        attachObserver()
    }

    private fun attachObserver() {
        if (isNetworkConnected()) {
            binding.myViewModel!!.userDataResponse.observe(this) { response ->
                usersList.addAll(response)
                setups()
            }
        } else {
            binding.myViewModel!!.getUsersDataBase()?.observe(this) { response ->
                usersList.addAll(response)
                setups()
            }
        }
    }

    private fun setups() {
        binding.rvMain.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = UsersAdapter(context, usersList)
        }
    }

    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }
}