package com.example.mvvm_data_binding_demo.roomDatbase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvm_data_binding_demo.data.Users

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAll(): LiveData<List<Users>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(usersList: Users)
}

