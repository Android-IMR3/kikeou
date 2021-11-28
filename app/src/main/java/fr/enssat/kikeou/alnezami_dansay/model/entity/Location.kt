package fr.enssat.kikeou.alnezami_dansay.model.entity

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
class Location(var day1:String,var day2:String,var day3:String,var day4:String,var day5:String,): Parcelable {
   constructor():this("","","","","")
}