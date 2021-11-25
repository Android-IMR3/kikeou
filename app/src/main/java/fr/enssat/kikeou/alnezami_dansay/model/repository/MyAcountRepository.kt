package fr.enssat.kikeou.alnezami_dansay.model.repository

import androidx.lifecycle.LiveData
import fr.enssat.kikeou.alnezami_dansay.model.dao.MyAccountDao
import fr.enssat.kikeou.alnezami_dansay.model.entity.MyAccount

class MyAcountRepository (private val myAccountDao : MyAccountDao) {
    fun getMyaccount(): LiveData<List<MyAccount>>{
       return myAccountDao.getMyAccount()
    }
    suspend fun register(myacount : MyAccount) {
        myAccountDao.register(myacount)
    }

}