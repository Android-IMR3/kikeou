package fr.enssat.kikeou.alnezami_dansay.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import fr.enssat.kikeou.alnezami_dansay.model.KikeouDataBase
import fr.enssat.kikeou.alnezami_dansay.model.repository.AgendaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
open class UsabelViewModel(application: Application) : AndroidViewModel(application) {

    private val respository: AgendaRepository
    val allAgendas: LiveData<List<Agenda>>
    val myAcount: LiveData<Agenda>
    val count: LiveData<Int>

    init {
        val agendaDao = KikeouDataBase.getDatabase(application).agendaDao()
        respository = AgendaRepository(agendaDao)
        myAcount = respository.getMyaccount
        allAgendas = respository.allAgendas
        count = respository.count
    }
    fun signUp(a : Agenda){

        viewModelScope.launch(Dispatchers.IO){
            respository.insert(a)
        }

    }
    fun updateMyProfile(a: Agenda){
        viewModelScope.launch(Dispatchers.IO){
            a.id =1//admin id
            respository.update(a)
        }
    }
    fun updateAgenda(a: Agenda){
        viewModelScope.launch(Dispatchers.IO){
            respository.update(a)
        }
    }
    fun addAgenda(agenda: Agenda) {
        viewModelScope.launch(Dispatchers.IO) {
            respository.insert(agenda)

        }
    }
    fun deleteAgenda(agenda: Agenda) {
        viewModelScope.launch(Dispatchers.IO) {
            respository.delete(agenda)
        }
    }
    fun getJson(agenda: Agenda): String{
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<Agenda> = moshi.adapter(Agenda::class.java)
        val json = adapter.toJson(agenda)
        return json
    }
}