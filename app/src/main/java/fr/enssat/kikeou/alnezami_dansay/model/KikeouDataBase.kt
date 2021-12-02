package fr.enssat.kikeou.alnezami_dansay.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.enssat.kikeou.alnezami_dansay.model.converter.Converters
import fr.enssat.kikeou.alnezami_dansay.model.dao.AgendaDao
import fr.enssat.kikeou.alnezami_dansay.model.entity.Agenda
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


@Database(entities = arrayOf( Agenda::class), version = 1, exportSchema = false)
@TypeConverters(Converters::class)
public abstract class KikeouDataBase : RoomDatabase() {


    abstract fun agendaDao(): AgendaDao

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
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

