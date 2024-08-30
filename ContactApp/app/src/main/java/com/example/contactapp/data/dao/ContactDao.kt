package com.example.contactapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.contactapp.data.tables.Contacts
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Upsert
    fun saveUpdateContact(contact : Contacts)

    @Delete
    fun deleteContact(contact: Contacts)

    @Query("SELECT * FROM contact")
    fun getAllContacts() : List<Contacts>

}