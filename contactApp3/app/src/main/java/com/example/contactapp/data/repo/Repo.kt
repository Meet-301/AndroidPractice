package com.example.contactapp.data.repo

import com.example.contactapp.data.database.ContactAppDatabase
import com.example.contactapp.data.tables.Contact
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repo @Inject constructor (private val database: ContactAppDatabase) {

    suspend fun upsertContact(contact: Contact) {
        database.getContactDao().upsertContact(contact)
    }

    suspend fun deleteContact(contact: Contact) {
        database.getContactDao().deleteContact(contact)
    }

    fun getAllContacts() : Flow<List<Contact>> {
        return database.getContactDao().getAllContacts()
    }

}