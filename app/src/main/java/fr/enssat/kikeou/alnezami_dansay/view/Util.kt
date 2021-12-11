package fr.enssat.kikeou.alnezami_dansay.view


import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar

import android.os.Build
import android.text.format.DateFormat
import android.util.Log
import androidx.annotation.RequiresApi

import androidx.viewbinding.ViewBinding
import com.squareup.picasso.Picasso
import fr.enssat.kikeou.alnezami_dansay.R
import fr.enssat.kikeou.alnezami_dansay.databinding.*
import fr.enssat.kikeou.alnezami_dansay.model.entity.Agenda
import fr.enssat.kikeou.alnezami_dansay.model.entity.Contact
import fr.enssat.kikeou.alnezami_dansay.model.entity.LOC
import fr.enssat.kikeou.alnezami_dansay.model.entity.Status
import java.time.DayOfWeek
import java.time.LocalDate

import java.time.temporal.ChronoField
import java.util.*


@RequiresApi(Build.VERSION_CODES.N)
fun bindingAgenda(a: Agenda,binding : ViewBinding){

    when(binding){
        is FragmentContactBinding  ->{
            binding.nameUser.setText(a.name);
            binding.emailUser.setText(a.contact.get(0).value)
            binding.phoneUser.setText(a.contact.get(1).value)
            binding.fbUser.setText(a.contact.get(2).value)
            Picasso.get().load(a.photo).into(binding.appBarImage)
            binding.btnDay1.setImageResource(setImageByStatus(a.loc.get(0).value))
            binding.btnDay2.setImageResource(setImageByStatus(a.loc.get(1).value))
            binding.btnDay3.setImageResource(setImageByStatus(a.loc.get(2).value))
            binding.btnDay4.setImageResource(setImageByStatus(a.loc.get(3).value))
            binding.btnDay5.setImageResource(setImageByStatus(a.loc.get(4).value))
            binding.statusImg.setImageResource(getDayStatus(a.loc))
            val week = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LocalDate.now().with(ChronoField.ALIGNED_WEEK_OF_YEAR, a.week.toLong())
            } else {
                TODO("VERSION.SDK_INT < O")
            }

            val start = week.with(DayOfWeek.MONDAY)
            binding.weekHome3.setText("Week number "+a.week.toString()+" of "+start)
        }
        is FragmentHomeBinding  ->{
           try {
               binding.nameUser.setText(a.name)

               binding.emailUser.setText(a.contact.get(0).value)
               binding.phoneUser.setText(a.contact.get(1).value)
               binding.fbUser.setText(a.contact.get(2).value)
               Picasso.get().load(a.photo).into(binding.appBarImage)
               binding.btnDay1.setImageResource(setImageByStatus(a.loc.get(0).value))
               binding.btnDay2.setImageResource(setImageByStatus(a.loc.get(1).value))
               binding.btnDay3.setImageResource(setImageByStatus(a.loc.get(2).value))
               binding.btnDay4.setImageResource(setImageByStatus(a.loc.get(3).value))
               binding.btnDay5.setImageResource(setImageByStatus(a.loc.get(4).value))
               binding.statusImg.setImageResource(getDayStatus(a.loc))
               val week = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                   LocalDate.now().with(ChronoField.ALIGNED_WEEK_OF_YEAR, a.week.toLong())
               } else {
                   TODO("VERSION.SDK_INT < O")
               }
               //local date to date
               val date1 = SimpleDateFormat("yyyy-MM-dd").parse(week.toString())
               //formatting
               val formatter = SimpleDateFormat("dd/MM/yyyy")
               val format = formatter.format(date1)
               val start = week.with(DayOfWeek.MONDAY)
               binding.weekHome.setText("Week number " + a.week.toString() + " of " + start)
           }catch (e: Exception) {
               Log.i("error","error"+e)
           }
        }
        is FragmentUpdateBinding ->{
            binding.nameUser.setText(a.name);
            binding.emailUser.setText(a.contact.get(0).value)
            binding.phoneUser.setText(a.contact.get(1).value)
            binding.fbUser.setText(a.contact.get(2).value)
//to get date from the number of week
            val week = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LocalDate.now().with(ChronoField.ALIGNED_WEEK_OF_YEAR, a.week.toLong())
            } else {
                TODO("VERSION.SDK_INT < O")
            }
            //local date to date
            val date1 = SimpleDateFormat("yyyy-MM-dd").parse(week.toString())
            //formatting
            val formatter = SimpleDateFormat("dd/MM/yyyy")
            val format = formatter.format(date1)
            binding.weekUser.setText(format.toString())
            binding.photoUser.setText(a.photo)
            binding.btnDay1.setImageResource(setImageByStatus(a.loc.get(0).value))
            binding.btnDay2.setImageResource(setImageByStatus(a.loc.get(1).value))
            binding.btnDay3.setImageResource(setImageByStatus(a.loc.get(2).value))
            binding.btnDay4.setImageResource(setImageByStatus(a.loc.get(3).value))
            binding.btnDay5.setImageResource(setImageByStatus(a.loc.get(4).value))
        }
        is FragmentFormNewContactBinding ->{
            binding.nameUser.setText(a.name);
            binding.emailUser.setText(a.contact.get(0).value)
            binding.phoneUser.setText(a.contact.get(1).value)
            binding.fbUser.setText(a.contact.get(2).value)
            Picasso.get().load(a.photo).into(binding.appBarImage)
            binding.btnDay1.setImageResource(setImageByStatus(a.loc.get(0).value))
            binding.btnDay2.setImageResource(setImageByStatus(a.loc.get(1).value))
            binding.btnDay3.setImageResource(setImageByStatus(a.loc.get(2).value))
            binding.btnDay4.setImageResource(setImageByStatus(a.loc.get(3).value))
            binding.btnDay5.setImageResource(setImageByStatus(a.loc.get(4).value))
            binding.statusImg.setImageResource(getDayStatus(a.loc))
            val week = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LocalDate.now().with(ChronoField.ALIGNED_WEEK_OF_YEAR, a.week)
            } else {
                TODO("VERSION.SDK_INT < O")
            }

            val start = week.with(DayOfWeek.MONDAY)
            binding.weekHome2.setText("Week number "+a.week.toString()+" of "+start)
        }
    }

}
fun  bindingLocation(a: List<LOC>,binding : ViewBinding){
    when(binding){
        is FragmentRegisterBinding ->{
            binding.btnDay1.setImageResource(setImageByStatus(a.get(0).value))
            binding.btnDay2.setImageResource(setImageByStatus(a.get(1).value))
            binding.btnDay3.setImageResource(setImageByStatus(a.get(2).value))
            binding.btnDay4.setImageResource(setImageByStatus(a.get(3).value))
            binding.btnDay5.setImageResource(setImageByStatus(a.get(4).value))
        }
    }

}
fun bindingLocationBtnsListener(locList :List<LOC>, binding : ViewBinding){
    when(binding){
        is FragmentRegisterBinding  ->{
            binding.btnDay1.setOnClickListener {
                locList.get(0).value=updataStatus( locList.get(0).value)
                locList.get(0).day =1
                binding.btnDay1.setImageResource(setImageByStatus( locList.get(0).value))

            }
            binding.btnDay2.setOnClickListener {
                locList.get(1).value= updataStatus( locList.get(1).value)
                locList.get(1).day =2
                binding.btnDay2.setImageResource(setImageByStatus( locList.get(1).value))

            }
            binding.btnDay3.setOnClickListener {
                locList.get(2).value= updataStatus( locList.get(2).value)
                locList.get(2).day= 3
                binding.btnDay3.setImageResource(setImageByStatus( locList.get(2).value))

            }
            binding.btnDay4.setOnClickListener {
                locList.get(3).value = updataStatus( locList.get(3).value)
                locList.get(3).day= 4
                binding.btnDay4.setImageResource(setImageByStatus( locList.get(3).value))
            }
            binding.btnDay5.setOnClickListener {
                locList.get(4).value=updataStatus( locList.get(4).value)
                locList.get(4).day= 5
                binding.btnDay5.setImageResource(setImageByStatus( locList.get(4).value))

            }
        }
        is FragmentUpdateBinding ->{
            binding.btnDay1.setOnClickListener {
                locList.get(0).value=updataStatus( locList.get(0).value)
                locList.get(0).day =1
                binding.btnDay1.setImageResource(setImageByStatus( locList.get(0).value))

            }
            binding.btnDay2.setOnClickListener {
                locList.get(1).value= updataStatus( locList.get(1).value)
                locList.get(1).day =2
                binding.btnDay2.setImageResource(setImageByStatus( locList.get(1).value))

            }
            binding.btnDay3.setOnClickListener {
                locList.get(2).value= updataStatus( locList.get(2).value)
                locList.get(2).day= 3
                binding.btnDay3.setImageResource(setImageByStatus( locList.get(2).value))

            }
            binding.btnDay4.setOnClickListener {
                locList.get(3).value = updataStatus( locList.get(3).value)
                locList.get(3).day= 4
                binding.btnDay4.setImageResource(setImageByStatus( locList.get(3).value))
            }
            binding.btnDay5.setOnClickListener {
                locList.get(4).value=updataStatus( locList.get(4).value)
                locList.get(4).day= 5
                binding.btnDay5.setImageResource(setImageByStatus( locList.get(4).value))

            }
        }
    }


}
@RequiresApi(Build.VERSION_CODES.O)
fun getAgenda(loc: List<LOC>,binding:ViewBinding):Agenda{
    var name=""
    var weekOfYear = 0
    var photo = ""
    var l = listOf<Contact>()
    when(binding){
        is FragmentRegisterBinding ->{
             name = binding.nameUser.text.toString()
            val week1 = binding.weekUser.text.toString()
            val format = "dd/mm/yyyy"
            val df = SimpleDateFormat(format)
            val date: Date = df.parse(week1)
            val cal = Calendar.getInstance()
            cal.time = date
            weekOfYear = cal[Calendar.WEEK_OF_YEAR]
             photo = binding.photoUser.text.toString();
            if(photo.isEmpty()){
                photo = "https://source.unsplash.com/1600x900/?avatar,person"
            }
            val email = binding.mailUser.text.toString();
            val phone = binding.phoneUser.text.toString();
            val fb = binding.fbUser.text.toString()
             l = listOf(Contact("email",email), Contact("phone",phone), Contact("FaceBook",fb))
        }
        is FragmentUpdateBinding ->
        {
            val name = binding.nameUser.text.toString();

            val week1 = binding.weekUser.text.toString()
            var weekOfYear = 0
            val format = "dd/MM/yyyy"
            val df = SimpleDateFormat(format)
            val date: Date = df.parse(week1)
            val cal = Calendar.getInstance()
            cal.time = date
            weekOfYear = cal[Calendar.WEEK_OF_YEAR]
            var photo = binding.photoUser.text.toString();
            if(photo.isEmpty()){
                photo = "https://source.unsplash.com/1600x900/?avatar,person"
            }
            val email = binding.emailUser.text.toString();
            val phone = binding.phoneUser.text.toString();
            val fb = binding.fbUser.text.toString()
            var l = listOf(Contact("email",email),Contact("phone",phone),Contact("FaceBook",fb))



        }
    }
    return Agenda(0,name,photo, l,weekOfYear.toLong(),loc )

}
fun updataStatus( loc: String):String{
    var res=""
    res = when(loc){
        Status.OFF.name,Status.Off.name -> {
            Status.HOME.name
        }
        Status.HOME.name,Status.teletravail.name -> {
            Status.WORK.name
        }
        Status.WORK.name,"WF 036" -> {
            Status.OFF.name
        }
        else -> {
            Status.WORK.name
        }
    }
    return res
}
@RequiresApi(Build.VERSION_CODES.N)
fun getDayStatus(location: List<LOC>):Int{

    val calendar = Calendar.getInstance()
    val weekDay = calendar[Calendar.DAY_OF_WEEK]

    var path = 0
    path = when (weekDay) {
      Calendar.MONDAY-> {
            setImageByStatus( location.get(0).value)
        }
       Calendar.TUESDAY-> {
            setImageByStatus(location.get(1).value)
        }
        Calendar.WEDNESDAY -> {
            setImageByStatus(location.get(2).value)
        }
        Calendar.THURSDAY -> {
            setImageByStatus(location.get(3).value)
        }
       Calendar.FRIDAY-> {
            setImageByStatus(location.get(4).value)
        }
        else ->   setImageByStatus(location.get(0).value)
    }
    Log.i("utils","fraiday"+weekDay)
    return path
}
fun setImageByStatus(location: String):Int{
    var path=0
    path = when (location){
        Status.OFF.name, Status.Off.name -> {
            R.drawable.vacation
        }
        Status.HOME.name, Status.teletravail.name -> {
            R.drawable.homepage
        }
        Status.WORK.name,"WF 036" -> {
            R.drawable.work

        }
        else -> {
            R.drawable.homepage
        }
    }
    return  path
}
