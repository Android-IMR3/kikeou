package fr.enssat.kikeou.alnezami_dansay.view.qr

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import fr.enssat.kikeou.alnezami_dansay.database.KikeouDataBase
import fr.enssat.kikeou.alnezami_dansay.database.dao.MyAccountDao
import fr.enssat.kikeou.alnezami_dansay.database.entity.MyAccount
import fr.enssat.kikeou.alnezami_dansay.database.entity.Person
import fr.enssat.kikeou.alnezami_dansay.database.repository.MyAcountRepository
import fr.enssat.kikeou.alnezami_dansay.database.repository.PersonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class QrViewModel(application: Application) : AndroidViewModel(application) {
   // val my_account: LiveData<MyAccount>
    val respository: MyAcountRepository
    val test = "hey"


    init {
        val myAccountDao = KikeouDataBase.getDatabase(application).myAccountDao()

        respository = MyAcountRepository(myAccountDao)

       // my_account = respository.myAccount
    }

    fun register(myAccount: MyAccount) {
        viewModelScope.launch(Dispatchers.IO) {
            respository.register(myAccount)
        }
    }
}