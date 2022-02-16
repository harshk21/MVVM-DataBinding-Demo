package com.example.mvvm_data_binding_demo.roomDatbase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvm_data_binding_demo.data.ProductsItem
import com.example.mvvm_data_binding_demo.utils.Constants

@Database(entities = [ProductsItem::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        var roomDb: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase? {
            roomDb = Room.databaseBuilder(
                context,
                UserDatabase::class.java, Constants.DATABASE_NAME
            ).fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
            return roomDb
        }
    }
}
