package fr.enssat.kikeou.alnezami_dansay.model.repository

import androidx.lifecycle.LiveData
import fr.enssat.kikeou.alnezami_dansay.model.dao.AgendaDao
import fr.enssat.kikeou.alnezami_dansay.model.entity.Agenda

class AgendaRepository(private val dao: AgendaDao) {
    val allAgendas: LiveData<List<Agenda>> = dao.getAlphabetizedAgendas()
    val count : LiveData<Int> = dao.count()
    val getMyaccount  = dao.getMyaccount()


    suspend fun insert(agenda: Agenda) {
        dao.insert(agenda)
    }
    suspend fun update(agenda: Agenda) {
        dao.update(agenda)
    }
    suspend fun delete(agenda: Agenda) {
        dao.delete(agenda)
    }

}