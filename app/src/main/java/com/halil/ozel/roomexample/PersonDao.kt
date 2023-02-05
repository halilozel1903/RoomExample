package com.halil.ozel.roomexample

import androidx.room.Dao
import androidx.room.Query

@Dao
interface PersonDao {
    @Query("SELECT * FROM person")
    suspend fun allPerson(): List<Person>
}