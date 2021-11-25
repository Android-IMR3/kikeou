package fr.enssat.kikeou.alnezami_dansay.view.qr

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import fr.enssat.kikeou.alnezami_dansay.model.KikeouDataBase
import fr.enssat.kikeou.alnezami_dansay.model.entity.MyAccount
import fr.enssat.kikeou.alnezami_dansay.model.repository.MyAcountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class QrViewModel(application: Application) : AndroidViewModel(application) {
    var my_account: LiveData<List<MyAccount>>
    val respository: MyAcountRepository
    val test = "hey how are yoou im ibra"


    init {
        val myAccountDao = KikeouDataBase.getDatabase(application).myAccountDao()

        respository = MyAcountRepository(myAccountDao)

        my_account = respository.getMyaccount()

    }

    fun register(myAccount: MyAccount) {
        viewModelScope.launch(Dispatchers.IO) {
            respository.register(myAccount)
        }
    }
    fun getMyaccount() {
        viewModelScope.launch(Dispatchers.IO) {
            respository.getMyaccount()
        }
    }
    fun getJson(): String{
        Log.i("qr view model",""+my_account.toString())
        val myacount = MyAccount(0,"firstname","alnezami","ueue",44,"0988766544","ibrahim@hmail.com","Lannion")
       // this.register(myacount)
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<MyAccount> = moshi.adapter(MyAccount::class.java)
        val json = adapter.toJson(myacount)
        return json
    }
}