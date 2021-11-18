package fr.enssat.kikeou.alnezami_dansay.database.repository

import androidx.lifecycle.LiveData
import fr.enssat.kikeou.alnezami_dansay.database.dao.MyAccountDao
import fr.enssat.kikeou.alnezami_dansay.database.entity.MyAccount
import fr.enssat.kikeou.alnezami_dansay.database.entity.Person

class MyAcountRepository (private val myAccountDao : MyAccountDao) {
    val myAccount: LiveData<MyAccount> = myAccountDao.getMyAccount()
    suspend fun register(myacount : MyAccount) {
        myAccountDao.register(myacount)
    }

}