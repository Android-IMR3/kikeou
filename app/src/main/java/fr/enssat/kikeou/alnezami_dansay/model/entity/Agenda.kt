package fr.enssat.kikeou.alnezami_dansay.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "agenda_table")
class Agenda(@PrimaryKey(autoGenerate = true) val id: Int,
             @ColumnInfo(name = "day") val name: String,
             @ColumnInfo(name = "day") val day: String,
             @ColumnInfo(name = "status") val status: String) {
}