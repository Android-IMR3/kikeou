package fr.enssat.kikeou.alnezami_dansay.model.repository

import androidx.annotation.WorkerThread
import fr.enssat.kikeou.alnezami_dansay.model.dao.AgendaDao
import fr.enssat.kikeou.alnezami_dansay.model.entity.Agenda

class AgendaRepository(private val dao: AgendaDao) {
    val allAgendas = dao.getAlphabetizedAgendas()
    val myacount = dao.getMyaccount()
    // call on a non-UI thread
    @WorkerThread
    suspend fun insert(agenda: Agenda) {
        dao.insert(agenda)
    }
}