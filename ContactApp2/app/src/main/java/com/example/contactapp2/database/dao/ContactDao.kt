package com.example.contactapp2.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.contactapp2.database.tables.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Upsert
    suspend fun upsertContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contacts: List<Contact>)

    @Query("SELECT * FROM contact_table")
    fun getContacts(): Flow<List<Contact>>

    @Query("SELECT * FROM contact_table WHERE name = :name AND phone = :number")
    fun isContactAlreadyExist(name : String, number : String) : List<Contact>

    @Query("SELECT * FROM contact_table WHERE phone = :number")
    fun isNumberAlreadyExist(number : String) : List<Contact>
}