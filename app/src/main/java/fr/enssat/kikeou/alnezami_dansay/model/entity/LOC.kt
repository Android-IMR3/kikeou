package fr.enssat.kikeou.alnezami_dansay.model.entity

import android.location.Location
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
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
    val contact: Contacts,
    val week: Long,
    val loc: LOCs
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
@Parcelize
@JsonClass(generateAdapter = true)
class Contacts(val contacts :List<Contact>):Parcelable{

    public fun toJson(): String {
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<Contacts> = moshi.adapter(Contacts::class.java)
        return adapter.toJson(this)
    }


    companion object {
        public fun fromJson(json: String): Contacts? {
            val moshi = Moshi.Builder().build()
            val adapter: JsonAdapter<Contacts> = moshi.adapter(Contacts::class.java)
            return adapter.fromJson(json)
        }
    }

}

@Parcelize
@JsonClass(generateAdapter = true)
class Contact (
    val key: String,
    val value: String
): Parcelable{
    constructor():this("", "")
    public fun toJson(): String {
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<Contact> = moshi.adapter(Contact::class.java)
        return adapter.toJson(this)
    }

    companion object {
        public fun fromJson(json: String): Contact? {
            val moshi = Moshi.Builder().build()
            val adapter: JsonAdapter<Contact> = moshi.adapter(Contact::class.java)
            return adapter.fromJson(json)
        }
    }
}



@Parcelize
@JsonClass(generateAdapter = true)
class LOCs(var locs:List<LOC>):Parcelable{

    public fun toJson(): String {
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<LOCs> = moshi.adapter(LOCs::class.java)
        return adapter.toJson(this)
    }


    companion object {
        public fun fromJson(json: String): LOCs? {
            val moshi = Moshi.Builder().build()
            val adapter: JsonAdapter<LOCs> = moshi.adapter(LOCs::class.java)
            return adapter.fromJson(json)
        }
    }
}

@Parcelize
@JsonClass(generateAdapter = true)
data class LOC (
    val day: Long,
    var value: String
): Parcelable{
    constructor():this(1,Status.WORK.name)

    public fun toJson(): String {
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<LOC> = moshi.adapter(LOC::class.java)
        return adapter.toJson(this)
    }

    companion object {
        public fun fromJson(json: String): LOC? {
            val moshi = Moshi.Builder().build()
            val adapter: JsonAdapter<LOC> = moshi.adapter(LOC::class.java)
            return adapter.fromJson(json)
        }
    }
}

class Converters {
    @TypeConverter
    fun contactFromJson(value: String): Contact? {
        return Contact.fromJson(value)
    }

    @TypeConverter
    fun contactToJson(contact: Contact?): String? {
        return contact?.toJson()
    }
    @TypeConverter
    fun contactsFromJson(value: String): Contacts? {
        return Contacts.fromJson(value)
    }

    @TypeConverter
    fun contactsToJson(contact: Contacts?): String? {
        return contact?.toJson()
    }

    @TypeConverter
    fun locFromJson(value: String): LOC? {
        return LOC.fromJson(value)
    }

    @TypeConverter
    fun locToJson(loc: LOC?): String? {
        return loc?.toJson()
    }
    @TypeConverter
    fun locsFromJson(value: String): LOCs? {
        return LOCs.fromJson(value)
    }

    @TypeConverter
    fun locsToJson(loc: LOCs?): String? {
        return loc?.toJson()
    }
}