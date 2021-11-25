package fr.enssat.kikeou.alnezami_dansay.model.entity

import android.os.Parcelable
import androidx.room.*
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize


@Parcelize
@JsonClass(generateAdapter = true)
@Entity(tableName = "person_table")
class Person(
            @PrimaryKey(autoGenerate = true)
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
): Parcelable {

}