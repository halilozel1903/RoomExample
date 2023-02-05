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

        controlPerson()
        // getOnePerson()
        // searchPerson()
        // getRandomPerson()
        // deletePerson()
        // insertPerson()
        // updatePerson()
        // getPerson()
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

    private fun deletePerson(){
        CoroutineScope(Dispatchers.Main).launch {
            val deletePerson = Person(13,"",0)
            personDao.deletePerson(deletePerson)
        }
    }

    private fun getRandomPerson(){
        CoroutineScope(Dispatchers.Main).launch {
            val list = personDao.randomPerson()

            for (person in list){
                Log.e("Person ID",person.person_id.toString())
                Log.e("Person Name", person.person_name)
                Log.e("Person Age",person.person_age.toString())
            }
        }
    }

    private fun searchPerson(){
        CoroutineScope(Dispatchers.Main).launch {
            val list = personDao.searchPerson("z")

            for (person in list){
                Log.e("Person ID",person.person_id.toString())
                Log.e("Person Name", person.person_name)
                Log.e("Person Age",person.person_age.toString())
            }
        }
    }

    private fun getOnePerson(){
        CoroutineScope(Dispatchers.Main).launch {
            val person = personDao.getPerson(11)
            Log.e("Person ID", person.person_id.toString())
            Log.e("Person Name", person.person_name)
            Log.e("Person Age", person.person_age.toString())
        }
    }

    private fun controlPerson(){
        CoroutineScope(Dispatchers.Main).launch {
            val size = personDao.controlPerson("Taylor Swift")
            Log.e("Person Size: ", size.toString())
        }
    }
}