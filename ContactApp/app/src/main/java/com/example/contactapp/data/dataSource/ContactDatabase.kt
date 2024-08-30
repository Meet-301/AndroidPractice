package com.example.contactapp.data.dataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.contactapp.data.dao.ContactDao
import com.example.contactapp.data.tables.Contacts

@Database(entities = [Contacts::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {

    abstract fun dao() : ContactDao

}