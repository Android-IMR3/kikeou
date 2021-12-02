package fr.enssat.kikeou.alnezami_dansay.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import kotlinx.parcelize.Parcelize


@Parcelize
@JsonClass(generateAdapter = true)
@Entity(tableName = "agenda_table")
class Agenda(
    @PrimaryKey(autoGenerate = true) var id: Int,
    val name: String,
    var photo: String,
    val contact: List<Contact>,
    val week: Long,
    val loc: List<LOC>
): Parcelable {

    public fun toJson(): String {
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<Agenda> = moshi.adapter(Agenda::class.java)
        return adapter.toJson(this)
    }



    companion object {
        public fun fromJson(json: String): Agenda? {
            val moshi = Moshi.Builder().build()
            val adapter: JsonAdapter<Agenda> = moshi.adapter(Agenda::class.java)
            return adapter.fromJson(json)
        }

    }
}