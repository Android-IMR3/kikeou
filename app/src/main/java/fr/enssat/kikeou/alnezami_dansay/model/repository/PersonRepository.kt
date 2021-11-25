package fr.enssat.kikeou.alnezami_dansay.model.repository

import androidx.lifecycle.LiveData
import fr.enssat.kikeou.alnezami_dansay.model.dao.PersonDao
import fr.enssat.kikeou.alnezami_dansay.model.entity.Person

class PersonRepository(private val personDao : PersonDao) {
    val readAllData: LiveData<List<Person>> = personDao.getAllPersons()
    suspend fun addPerson(person : Person) {
        personDao.addPerson(person)
    }
}