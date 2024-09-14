package com.example.contactapp2.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_table")
data class Contact(
    @PrimaryKey(autoGenerate = true) var id : Int? = null,
    var name : String,
    var email : String,
    var phone : String,
//    var image: ByteArray,
//    var DOB: Long
)