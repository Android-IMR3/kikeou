package fr.enssat.kikeou.alnezami_dansay.model.dao

import androidx.room.*
import fr.enssat.kikeou.alnezami_dansay.model.entity.Agenda
import kotlinx.coroutines.flow.Flow


@Dao
interface AgendaDao {

    // allowing the insert of agenda with conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(agenda: Agenda)

    @Query("DELETE FROM agenda_table")
    fun deleteAll()

    @Query("SELECT * FROM agenda_table WHERE id=1")
    fun getMyaccount(): Flow<Agenda>

    @Query("SELECT * FROM agenda_table ORDER BY name ASC")
    fun getAlphabetizedAgendas(): Flow<List<Agenda>>
}