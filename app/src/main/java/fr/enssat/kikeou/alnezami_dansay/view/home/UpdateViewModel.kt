package fr.enssat.kikeou.alnezami_dansay.view.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import fr.enssat.kikeou.alnezami_dansay.model.KikeouDataBase
import fr.enssat.kikeou.alnezami_dansay.model.repository.AgendaRepository
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class UpdateViewModel (application: Application)  : AndroidViewModel(application) {

    val respository: AgendaRepository


    init {
        val agendaDao = KikeouDataBase.getDatabase(application).agendaDao()
        respository = AgendaRepository(agendaDao)
    }
}