package fr.enssat.kikeou.alnezami_dansay.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import kotlinx.parcelize.Parcelize
import com.google.gson.reflect.TypeToken

import com.google.gson.Gson
import java.lang.reflect.Type


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
data class LOC (
    var day: Long,
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
    fun fromContactList(countryLang: List<Contact?>?): String? {
        if (countryLang == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Contact?>?>() {}.getType()
        return gson.toJson(countryLang, type)
    }

    @TypeConverter
    fun toContactList(countryLangString: String?): List<Contact?>? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Contact?>?>() {}.getType()
        return gson.fromJson<List<Contact>>(countryLangString, type)
    }
    @TypeConverter
    fun fromLOCList(countryLang: List<LOC?>?): String? {
        if (countryLang == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<LOC?>?>() {}.getType()
        return gson.toJson(countryLang, type)
    }

    @TypeConverter
    fun toLOCList(countryLangString: String?): List<LOC?>? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<LOC?>?>() {}.getType()
        return gson.fromJson<List<LOC>>(countryLangString, type)
    }
    @TypeConverter
    fun contactFromJson(value: String): Contact? {
        return Contact.fromJson(value)
    }

    @TypeConverter
    fun contactToJson(contact: Contact?): String? {
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

}