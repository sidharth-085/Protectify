package com.sid.protectify.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_table")
data class ContactItemModel(
    val name: String,
    @PrimaryKey
    val number: String
)
