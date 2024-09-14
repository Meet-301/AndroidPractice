package com.example.contactapp2

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactapp2.database.ContactAppDatabase
import com.example.contactapp2.database.DBInstance
import com.example.contactapp2.database.tables.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactAppViewModel(
    val context : Context
) : ViewModel() {
    val db : ContactAppDatabase

    init {
        db = DBInstance.getInstance(context)
    }

    fun addUpdateContact(contact: Contact) {
        viewModelScope.launch(Dispatchers.IO) {
            db.contactDao().upsertContact(contact)
        }
    }

    fun deleteContact(contacts: List<Contact>) {
        viewModelScope.launch(Dispatchers.IO) {
            db.contactDao().deleteContact(contacts)
        }
    }
}