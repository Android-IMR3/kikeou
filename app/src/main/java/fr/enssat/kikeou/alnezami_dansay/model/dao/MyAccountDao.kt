package fr.enssat.kikeou.alnezami_dansay.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.enssat.kikeou.alnezami_dansay.model.entity.MyAccount


@Dao
interface MyAccountDao {
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        fun register(myAcount: MyAccount)

        @Query("SELECT * FROM my_account_table LIMIT 1")
        fun  getMyAccount():  LiveData<List<MyAccount>>


        @Update(onConflict = OnConflictStrategy.IGNORE)
         fun updateMyAccount(person: MyAccount)



}