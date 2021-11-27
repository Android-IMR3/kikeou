package fr.enssat.kikeou.alnezami_dansay.view.qr

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import fr.enssat.kikeou.alnezami_dansay.model.KikeouDataBase
import fr.enssat.kikeou.alnezami_dansay.model.entity.Agenda
import fr.enssat.kikeou.alnezami_dansay.model.entity.Contact
import fr.enssat.kikeou.alnezami_dansay.model.entity.Location
import fr.enssat.kikeou.alnezami_dansay.model.repository.AgendaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class QrViewModel(application: Application) : AndroidViewModel(application) {
    var my_account: Flow<Agenda>
    val respository: AgendaRepository


    init {
        val myAccountDao = KikeouDataBase.getDatabase(application).agendaDao()

        respository = AgendaRepository(myAccountDao)

        my_account = respository.myacount

    }

    fun register(myAccount: Agenda) {
        viewModelScope.launch(Dispatchers.IO) {
            respository.insert(myAccount)
        }
    }
    fun getMyaccount() {
        viewModelScope.launch(Dispatchers.IO) {
            respository.myacount
        }
    }
    fun getJson(): String{
        Log.i("qr view model",""+my_account.toString())
        var contacts = Contact("ibrahim","345678","test@fb")

        var loc = Location(1,"OFF")
        var myacount = Agenda(0,"ibrahim","tthttps://picsum.photos/200/300?random=1",34,loc,contacts)

        this.register(myacount)
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<Agenda> = moshi.adapter(Agenda::class.java)
        val json = adapter.toJson(myacount)
        return json
    }
}