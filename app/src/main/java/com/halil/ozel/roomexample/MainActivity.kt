package com.halil.ozel.roomexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var personDatabase: PersonDatabase
    private lateinit var personDao: PersonDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        personDatabase = PersonDatabase.accessToDatabase(this)!!
        personDao = personDatabase.getPersonDao()

        // insertPerson()
        updatePerson()
        getPerson()
    }

    private fun getPerson(){
        CoroutineScope(Dispatchers.Main).launch {
            val list = personDao.allPerson()


            for (person in list){
                Log.e("Person ID",person.person_id.toString())
                Log.e("Person Name", person.person_name)
                Log.e("Person Age",person.person_age.toString())
            }
        }
    }

    private fun insertPerson(){
        CoroutineScope(Dispatchers.Main).launch {
            val newPerson = Person(0,"Hannah Waddingham",48)
            personDao.insertPerson(newPerson)
        }
    }

    private fun updatePerson(){
        CoroutineScope(Dispatchers.Main).launch {
            val newPerson = Person(12,"Hilary Duff",35)
            personDao.updatePerson(newPerson)
        }
    }
}