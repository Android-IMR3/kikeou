package fr.enssat.kikeou.alnezami_dansay.view.list


/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.enssat.kikeou.alnezami_dansay.database.KikeouDataBase
import fr.enssat.kikeou.alnezami_dansay.database.dao.PersonDao
import fr.enssat.kikeou.alnezami_dansay.database.entity.Person
import fr.enssat.kikeou.alnezami_dansay.database.repository.PersonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi

import kotlinx.coroutines.launch

/**
 * ViewModel for SleepTrackerFragment.
 */
@InternalCoroutinesApi
class ListPersonViewModal(
   application: Application) : ViewModel() {
     var readAllData:LiveData<List<Person>>

     var respository:PersonRepository

    init {
        val personDao  = KikeouDataBase.getDatabase(application).personDao()

        respository = PersonRepository(personDao)

        readAllData = respository.readAllData
    }
fun addPerson(person:Person) {
    viewModelScope.launch(Dispatchers.IO){
        respository.addPerson(person)
    }
}
















}