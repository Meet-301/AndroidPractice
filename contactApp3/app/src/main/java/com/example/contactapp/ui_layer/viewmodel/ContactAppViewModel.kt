package com.example.contactapp.ui_layer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactapp.data.repo.Repo
import com.example.contactapp.data.tables.Contact
import com.example.contactapp.ui_layer.state.ContactState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactAppViewModel @Inject constructor(private val repo: Repo) : ViewModel() {

    private val contacts = repo.getAllContacts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(ContactState())

    val state = combine(_state, contacts) { _state, contacts ->
        _state.copy(contactList = contacts)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ContactState())

    fun upsertContact() {
        viewModelScope.launch {

            val contact = Contact(
                name = state.value.name.value,
                number = state.value.number.value,
                email = state.value.email.value
            )

            repo.upsertContact(contact)
        }
    }

}