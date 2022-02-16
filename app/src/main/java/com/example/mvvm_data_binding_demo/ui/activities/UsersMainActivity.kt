package com.example.mvvm_data_binding_demo.ui.activities


import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_data_binding_demo.R
import com.example.mvvm_data_binding_demo.adapters.UsersAdapter
import com.example.mvvm_data_binding_demo.data.ProductsItem
import com.example.mvvm_data_binding_demo.databinding.ActivityMainBinding
import com.example.mvvm_data_binding_demo.ui.UsersViewModel
import com.google.android.material.snackbar.Snackbar
import kotlin.properties.Delegates


class UsersMainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
    private val mViewModel: UsersViewModel by viewModels()
    private var usersList: ArrayList<ProductsItem> by Delegates.observable(ArrayList()) { _, oldValue, newValue ->
        Log.e("TAG", "New: $newValue")
    }
    private val adapterItem by lazy {
        UsersAdapter(this, usersList)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.myViewModel = mViewModel
        mViewModel.getUsers()
        attachObserver()
    }

    private fun attachObserver() {
        if (isNetworkConnected()) {
            binding.myViewModel!!.userDataResponse.observe(this) { response ->
                response?.forEach {
                    usersList.add(it)
                }
                setups()
            }
        } else {
            Snackbar.make(binding.root, "Not connected fetching local data", Snackbar.LENGTH_SHORT)
                .show()
            binding.myViewModel!!.getUsersDataBase()?.observe(this) { response ->
                usersList.addAll(response)
                setups()
            }
        }
    }


    private fun setups() {
        binding.rvMain.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterItem
        }
    }

    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }
}