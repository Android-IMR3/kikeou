package fr.enssat.kikeou.alnezami_dansay.database.dao

import androidx.room.*
import fr.enssat.kikeou.alnezami_dansay.database.entity.MyAccount
import kotlinx.coroutines.flow.Flow

@Dao
interface MyAccountDao {


        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun Register(person: MyAccount): Flow<MyAccount>


        @Update(onConflict = OnConflictStrategy.IGNORE)
        suspend fun updateMyAccount(person: MyAccount)

        @Query("SELECT * FROM my_account_table ORDER BY first_name ASC")
        fun getMyaAccount(): Flow<MyAccount>


}