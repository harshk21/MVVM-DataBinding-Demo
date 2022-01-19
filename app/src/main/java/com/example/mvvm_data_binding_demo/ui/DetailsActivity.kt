package com.example.mvvm_data_binding_demo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_data_binding_demo.R
import com.example.mvvm_data_binding_demo.data.Users
import com.example.mvvm_data_binding_demo.databinding.ActivityDetailsBinding
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class DetailsActivity : AppCompatActivity() {
    private lateinit var detailsBinding: ActivityDetailsBinding
    private lateinit var mViewModel: UsersViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        mViewModel = ViewModelProvider(this)[UsersViewModel::class.java]
        detailsBinding.detailViewModel = mViewModel
        detailsBinding.lifecycleOwner = this
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun data(item: Users) {
        mViewModel.setSingleUser(item)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

}
