package fr.enssat.kikeou.alnezami_dansay.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "my_account_table")
class MyAccount(@PrimaryKey(autoGenerate = true) val id: Int,
             @ColumnInfo(name = "first_name") val first_name: String,
             @ColumnInfo(name = "last_name") val last_name: String,
             @ColumnInfo(name = "photo") val photo: String,
             @ColumnInfo(name="contact_id") val contact :Long) {

}