package fr.enssat.kikeou.alnezami_dansay.model.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fr.enssat.kikeou.alnezami_dansay.model.entity.Contact
import fr.enssat.kikeou.alnezami_dansay.model.entity.LOC
import java.lang.reflect.Type


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