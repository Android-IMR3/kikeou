package fr.enssat.kikeou.alnezami_dansay.view.contact

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import fr.enssat.kikeou.alnezami_dansay.model.KikeouDataBase
import fr.enssat.kikeou.alnezami_dansay.model.repository.PersonRepository
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class ContactViewModel  (application: Application) : AndroidViewModel(application) {

    val respository: PersonRepository


    init {
        val personDao = KikeouDataBase.getDatabase(application).personDao()
        respository = PersonRepository(personDao)
    }
}