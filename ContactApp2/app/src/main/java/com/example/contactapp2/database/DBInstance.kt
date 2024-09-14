package com.example.contactapp2.database

import android.content.Context
import androidx.room.Room
import com.example.contactapp2.DB_NAME

object DBInstance {

    var dbInstance : ContactAppDatabase? = null

    fun getInstance(context: Context): ContactAppDatabase {
        synchronized(this) {
            if (dbInstance == null) {
                dbInstance = Room.databaseBuilder(context, ContactAppDatabase::class.java, DB_NAME).build()
            }
            return dbInstance!!
        }
    }

}