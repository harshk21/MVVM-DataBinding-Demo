package com.example.mvvm_data_binding_demo.roomDatbase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvm_data_binding_demo.data.ProductsItem

@Dao
interface UserDao {
    @Query("SELECT * FROM productsitem")
    fun getAll(): LiveData<List<ProductsItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(usersList: ProductsItem)
}

