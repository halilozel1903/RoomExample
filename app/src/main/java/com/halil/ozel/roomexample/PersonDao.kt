package com.halil.ozel.roomexample

import androidx.room.*

@Dao
interface PersonDao {
    @Query("SELECT * FROM person")
    suspend fun allPerson(): List<Person>

    @Insert
    suspend fun insertPerson(person: Person)

    @Update
    suspend fun updatePerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person)

    @Query("SELECT * FROM person ORDER BY RANDOM() LIMIT 3")
    suspend fun randomPerson(): List<Person>

    @Query("SELECT * FROM person WHERE person_name like '%' || :searchName || '%'")
    suspend fun searchPerson(searchName: String): List<Person>

    @Query("SELECT * FROM person WHERE person_id=:person_id")
    suspend fun getPerson(person_id: Int): Person
}