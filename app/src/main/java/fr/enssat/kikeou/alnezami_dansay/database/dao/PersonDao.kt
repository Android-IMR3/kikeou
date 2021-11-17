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
        suspend fun addPerson(person: Person): Flow<Person>
        @Delete
        suspend fun removePerson(person: Person)

        @Update(onConflict = OnConflictStrategy.IGNORE)
        suspend fun updatePerson(person: Person)

        @Query("DELETE FROM person_table")
        suspend fun deleteAll()

}