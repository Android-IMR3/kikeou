package fr.enssat.kikeou.alnezami_dansay.model.entity

import android.os.Parcelable

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
  class LOC (
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

