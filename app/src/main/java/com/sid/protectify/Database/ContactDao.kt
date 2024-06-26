package com.sid.protectify.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sid.protectify.Models.ContactItemModel

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contactModel: ContactItemModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllContacts(listOfContacts: List<ContactItemModel>)

    @Query("SELECT * FROM contact_table")
    fun getAllContacts(): LiveData<List<ContactItemModel>>
}