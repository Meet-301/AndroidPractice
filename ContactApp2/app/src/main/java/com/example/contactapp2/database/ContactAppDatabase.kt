package com.example.contactapp2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.contactapp2.database.dao.ContactDao
import com.example.contactapp2.database.tables.Contact

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactAppDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
}