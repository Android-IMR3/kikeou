package fr.enssat.kikeou.alnezami_dansay.view.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import fr.enssat.kikeou.alnezami_dansay.model.KikeouDataBase
import fr.enssat.kikeou.alnezami_dansay.model.entity.Agenda
import fr.enssat.kikeou.alnezami_dansay.model.repository.AgendaRepository
import fr.enssat.kikeou.alnezami_dansay.view.UsabelViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class HomeViewModel (application: Application): UsabelViewModel(application) {
}