package com.sid.protectify

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contactModel: ContactItemModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllContacts(listOfContacts: List<ContactItemModel>)

    @Query("SELECT * FROM contact_table")
    suspend fun getAllContacts(): List<ContactItemModel>
}