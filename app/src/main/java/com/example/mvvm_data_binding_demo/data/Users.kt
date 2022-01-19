package com.example.mvvm_data_binding_demo.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
@Entity
data class Users(
    @PrimaryKey(autoGenerate = true)
    val user_id: Int,
    @SerialName("email")
    val email: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("phone")
    val phone: String = "",
    @SerialName("username")
    val username: String = "",
    @SerialName("website")
    val website: String = ""
) : Parcelable