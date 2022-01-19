package com.example.mvvm_data_binding_demo.application

import android.app.Application
import com.example.mvvm_data_binding_demo.roomDatbase.UserDatabase

class MyApplication : Application() {

    companion object {
        var roomDatabase: UserDatabase? = null
    }

    init {

    }

    override fun onCreate() {
        super.onCreate()
        roomDatabase = UserDatabase.getInstance(this)


    }
}