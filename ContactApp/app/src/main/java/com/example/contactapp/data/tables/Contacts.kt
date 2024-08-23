package com.example.contactapp.data.tables

import androidx.room.Entity

@Entity
data class Contacts(
                    var id : Int,
                    var name: String,
                    var number: String,
                    var email: String,
                    var dob : Long,
                    var profile : ByteArray)