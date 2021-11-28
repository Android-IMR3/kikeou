package fr.enssat.kikeou.alnezami_dansay.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.enssat.kikeou.alnezami_dansay.model.entity.Agenda


@Dao
interface AgendaDao {

    // allowing the insert of agenda with conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(agenda: Agenda)

    @Update
    fun update(agenda: Agenda)

    @Delete
    fun delete(agenda: Agenda)

    @Query("DELETE FROM agenda_table")
    fun deleteAll()

    @Query("SELECT * FROM agenda_table WHERE id=1")
    fun getMyaccount(): LiveData<Agenda>

    @Query("SELECT * FROM agenda_table WHERE id>1 ORDER BY name ASC")
    fun getAlphabetizedAgendas(): LiveData<List<Agenda>>

    @Query("SELECT Count() FROM agenda_table ")
    fun count(): LiveData<Int>
}