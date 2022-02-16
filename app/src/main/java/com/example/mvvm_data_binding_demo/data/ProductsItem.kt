package com.example.mvvm_data_binding_demo.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class ProductsItem(
    val category: String,
    val description: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val image: String,
    val price: String,
    val title: String
): Parcelable