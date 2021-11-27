package fr.enssat.kikeou.alnezami_dansay.view.readQR

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import fr.enssat.kikeou.alnezami_dansay.model.KikeouDataBase
import fr.enssat.kikeou.alnezami_dansay.model.entity.Agenda
import fr.enssat.kikeou.alnezami_dansay.model.repository.AgendaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class FormNewContactViewModel  (application: Application) : AndroidViewModel(application) {

  val respository: AgendaRepository


  init {
    val personDao = KikeouDataBase.getDatabase(application).agendaDao()

    respository = AgendaRepository(personDao)
  }
  fun addPerson(agenda: Agenda) {
    viewModelScope.launch(Dispatchers.IO) {
      respository.insert(agenda)
    }
  }
}