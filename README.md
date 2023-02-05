# Room Database Example 📀 🖥️ 🖱️

![Room Database](https://miro.medium.com/v2/resize:fit:1400/format:webp/1*-OboqZDfl9-XTT8XA-b0cA.png)

Room is a persistence library, part of the Android Jetpack.

Room provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.

Room is now considered as a better approach for data persistence than SQLiteDatabase. It makes it easier to work with SQLiteDatabase objects in your app, decreasing the amount of boilerplate code and verifying SQL queries at compile time.

## Why use Room ⁉️
- Compile-time verification of SQL queries. each @Query and @Entity is checked at the compile time, that preserves your app from crash issues at runtime and not only it checks the only syntax, but also missing tables.
- Boilerplate code
- Easily integrated with other Architecture components (like LiveData)

## Components of Room DB

![Room Database](https://media.geeksforgeeks.org/wp-content/uploads/20210506144355/output.png)

Room has 3 main components of Room DB :
- Entity
- Dao
- Database

1. `Entity`: Represents a table within the database. Room creates a table for each class that has @Entity annotation, the fields in the class correspond to columns in the table. Therefore, the entity classes tend to be small model classes that don’t contain any logic.
2. `Dao`: DAOs are responsible for defining the methods that access the database. In the initial SQLite, we use the Cursor objects. With Room, we don’t need all the Cursor related code and can simply define our queries using annotations in the Dao class.
3. `Database`: Contains the database holder and serves as the main access point for the underlying connection to your app’s persisted, relational data.
To create a database we need to define an abstract class that extends RoomDatabase. This class is annotated with @Database, lists the entities contained in the database, and the DAOs which access them.


## Entity 🐐

```kotlin
@Entity("person")
data class Person(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("person_id") @NotNull var person_id: Int,
    @ColumnInfo("person_name") @NotNull var person_name: String,
    @ColumnInfo("person_age") @NotNull var person_age: Int
)
```

## Dao ⚔️

```kotlin
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

    @Query("SELECT count(*) FROM person WHERE person_name=:person_name")
    suspend fun controlPerson(person_name: String): Int
}
```

# Database 🔥

```kotlin
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
```

# Room Examples ⚖️

```kotlin
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
```

## Donation 💸

If this project help 💁 you, Can you give me a cup of coffee? ☕

[!["Buy Me A Coffee"](https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png)](https://www.buymeacoffee.com/halilozel1903)

## License ℹ️
```
MIT License

Copyright (c) 2023 Halil OZEL

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
