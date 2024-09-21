package com.example.contactapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.contactapp.data.dao.ContactDao
import com.example.contactapp.data.tables.Contact

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactAppDatabase : RoomDatabase() {
    abstract fun getContactDao(): ContactDao
}