package fr.enssat.kikeou.alnezami_dansay.model.entity

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

data class AG (
    val id: String,
    val name: String,
    val photo: String,
    val contact: List<Contact>,
    val week: Long,
    val loc: List<LOC>
) {

    public fun toJson(): String {
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<AG> = moshi.adapter(AG::class.java)
        return adapter.toJson(this)
    }

    companion object {
        public fun fromJson(json: String): AG? {
            val moshi = Moshi.Builder().build()
            val adapter: JsonAdapter<AG> = moshi.adapter(AG::class.java)
          return adapter.fromJson(json)
        }
    }
}

data class Contact (
    val key: String,
    val value: String
)

data class LOC (
    val day: Long,
    val value: String
)