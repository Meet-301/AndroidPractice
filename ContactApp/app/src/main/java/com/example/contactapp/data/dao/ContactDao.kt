package com.example.contactapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.contactapp.data.tables.Contacts

@Dao
interface ContactDao {

    @Upsert
    fun saveUpdateContact(contact : Contacts)

    @Delete
    fun deleteContact(contact: Contacts)

    @Query("SELECT * FROM contact")
    fun getAllContacts() : List<Contacts>

    @Query("SELECT * FROM contact WHERE name = :name AND number = :number")
    fun isContactAlreadyExist(name : String, number : String) : List<Contacts>

    @Query("SELECT * FROM contact WHERE number = :number")
    fun isNumberAlreadyExist(number : String) : List<Contacts>
}