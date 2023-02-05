package com.halil.ozel.roomexample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Person::class], version = 1)
abstract class PersonDatabase: RoomDatabase() {
    abstract fun getPersonDao(): PersonDao

    companion object {
        var INSTANCE: PersonDatabase? = null

        fun accessToDatabase(context: Context): PersonDatabase? {
            if (INSTANCE == null) {
                synchronized(PersonDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        PersonDatabase::class.java,
                        "contact.sqlite"
                    ).createFromAsset("contact.sqlite").build()
                }
            }
            return INSTANCE
        }
    }
}