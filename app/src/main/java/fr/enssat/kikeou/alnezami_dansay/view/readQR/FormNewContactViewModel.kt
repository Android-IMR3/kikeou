package fr.enssat.kikeou.alnezami_dansay.view.readQR

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import fr.enssat.kikeou.alnezami_dansay.model.KikeouDataBase
import fr.enssat.kikeou.alnezami_dansay.model.entity.Person
import fr.enssat.kikeou.alnezami_dansay.model.repository.MyAcountRepository
import fr.enssat.kikeou.alnezami_dansay.model.repository.PersonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class FormNewContactViewModel  (application: Application) : AndroidViewModel(application) {

  val respository: PersonRepository
  val test = "hey how are yoou im ibra"


  init {
    val personDao = KikeouDataBase.getDatabase(application).personDao()

    respository = PersonRepository(personDao)
  }
  fun addPerson(person: Person) {
    viewModelScope.launch(Dispatchers.IO) {
      respository.addPerson(person)
    }
  }
}