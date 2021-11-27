package fr.enssat.kikeou.alnezami_dansay.model.entity

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
class Location(var day:Int, var place:String): Parcelable {
}