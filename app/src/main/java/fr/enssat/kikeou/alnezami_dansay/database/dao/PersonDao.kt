package fr.enssat.kikeou.alnezami_dansay.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.enssat.kikeou.alnezami_dansay.database.entity.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

        @Query("SELECT * FROM person_table ORDER BY first_name ASC")
        fun getAllPersons(): LiveData<List<Person>>

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        fun addPerson(person: Person)
       @Delete
         fun removePerson(person: Person)

        @Update(onConflict = OnConflictStrategy.IGNORE)
         fun updatePerson(person: Person)

        @Query("DELETE FROM person_table")
         fun deleteAll()

}