package com.example.contactapp.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contactapp.data.dataSource.ContactDatabase
import com.example.contactapp.data.tables.Contacts

object DatabaseInstance {
//    lateinit var dbInstance : ContactDatabase

    fun getDB(context: Context) : ContactDatabase {
       return Room.databaseBuilder(context, ContactDatabase::class.java, "contact_db").allowMainThreadQueries().build()
    }

}