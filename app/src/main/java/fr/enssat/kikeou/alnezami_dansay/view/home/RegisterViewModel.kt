package fr.enssat.kikeou.alnezami_dansay.view.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import fr.enssat.kikeou.alnezami_dansay.model.KikeouDataBase
import fr.enssat.kikeou.alnezami_dansay.model.entity.Agenda
import fr.enssat.kikeou.alnezami_dansay.model.repository.AgendaRepository
import fr.enssat.kikeou.alnezami_dansay.view.UsabelViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class RegisterViewModel (application: Application)  : UsabelViewModel(application) {


}