package fr.enssat.kikeou.alnezami_dansay.model.dao

import androidx.room.*
import fr.enssat.kikeou.alnezami_dansay.model.entity.Person
import kotlinx.coroutines.flow.Flow


@Dao
interface AgendaDao {

    @Query("SELECT * FROM agenda_table")
    fun getAllAgendas(): Flow<List<Person>>


    @Query("DELETE FROM agenda_table")
    suspend fun deleteAll()

}