package fr.enssat.kikeou.alnezami_dansay.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import fr.enssat.kikeou.alnezami_dansay.database.dao.AgendaDao
import fr.enssat.kikeou.alnezami_dansay.database.dao.PersonDao
import fr.enssat.kikeou.alnezami_dansay.database.entity.Agenda
import fr.enssat.kikeou.alnezami_dansay.database.entity.Person
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import kotlinx.coroutines.launch
import okhttp3.internal.Internal
import okhttp3.internal.Internal.instance


@Database(entities = arrayOf(Person::class), version = 1, exportSchema = false)
public abstract class KikeouDataBase : RoomDatabase() {
    abstract fun personDao(): PersonDao


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: KikeouDataBase? = null

        @InternalCoroutinesApi
        fun getDatabase(context: Context): KikeouDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KikeouDataBase::class.java,
                    "kikeou_database"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

