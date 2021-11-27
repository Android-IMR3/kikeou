package fr.enssat.kikeou.alnezami_dansay.model.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize


@Parcelize
@JsonClass(generateAdapter = true)
@Entity(tableName = "agenda_table")
class Agenda(
    @PrimaryKey(autoGenerate = true) var id:Int,
    @ColumnInfo(name = "name") var name:String,
             @ColumnInfo(name = "photo") var photo:String,
             @ColumnInfo(name = "week") var week:Int,
             @Embedded
             var loc: Location,
             @Embedded
             var contact:Contact): Parcelable {


}