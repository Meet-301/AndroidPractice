package com.example.contactapp.data.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class Contacts(
                    @PrimaryKey(autoGenerate = true) var id : Int? = null,
                    var name: String,
                    var number: String,
                    var email: String,
                    var dob : Long,
                    var profile : ByteArray)