package fr.enssat.kikeou.alnezami_dansay.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
@Entity(tableName = "my_account_table")
class MyAccount(@PrimaryKey(autoGenerate = true)
                val id: Int,
             @ColumnInfo(name = "first_name")
             val first_name: String,
             @ColumnInfo(name = "last_name")
                val last_name: String,
             @ColumnInfo(name = "photo")
                val photo: String,
             @ColumnInfo(name="contact_id")
                val contact :Long,
            @ColumnInfo(name = "phone")
                val phone: String,
            @ColumnInfo(name = "email")
                val email: String,
            @ColumnInfo(name = "adresse")
                val adresse: String
             ) {
   override fun toString(): String{
        return this.first_name + " " + this.last_name +this.photo + this.contact
    }

}