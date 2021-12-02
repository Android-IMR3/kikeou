package fr.enssat.kikeou.alnezami_dansay.model.entity

import android.os.Parcelable
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import kotlinx.parcelize.Parcelize


@Parcelize
@JsonClass(generateAdapter = true)
class Contact (
    val key: String,
    val value: String
): Parcelable {
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
