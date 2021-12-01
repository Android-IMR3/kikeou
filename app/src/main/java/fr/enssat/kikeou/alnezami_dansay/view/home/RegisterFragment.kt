package fr.enssat.kikeou.alnezami_dansay.view.home

import android.R.attr
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import fr.enssat.kikeou.alnezami_dansay.R
import fr.enssat.kikeou.alnezami_dansay.databinding.FragmentRegisterBinding
import fr.enssat.kikeou.alnezami_dansay.model.entity.Agenda
import fr.enssat.kikeou.alnezami_dansay.model.entity.Contact
import fr.enssat.kikeou.alnezami_dansay.model.entity.Location
import fr.enssat.kikeou.alnezami_dansay.model.entity.Status
import kotlinx.coroutines.InternalCoroutinesApi


import android.R.attr.firstDayOfWeek
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    @InternalCoroutinesApi
    private lateinit var registerViewModel: RegisterViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        var loc =Location()
        bindingLocation(loc)
        var agenda: Agenda
        val btn = binding.btnSignup
        btn.setOnClickListener {
            agenda = getAgenda(loc)
            registerViewModel.signUp(agenda)
            findNavController().navigate(R.id.action_registerFragment_to_listFragment)
        }

        binding.btnDay1.setOnClickListener {
            loc.day1=updataStatus(loc.day1)
            binding.btnDay1.setImageResource(setImageByStatus(loc.day1))

        }

        binding.btnDay2.setOnClickListener {
            loc.day2= updataStatus(loc.day2)
            binding.btnDay2.setImageResource(setImageByStatus(loc.day2))

        }
        binding.btnDay3.setOnClickListener {
            loc.day3= updataStatus(loc.day3)
            binding.btnDay3.setImageResource(setImageByStatus(loc.day3))

        }
        binding.btnDay4.setOnClickListener {
            loc.day4 = updataStatus(loc.day4)
            binding.btnDay4.setImageResource(setImageByStatus(loc.day4))
        }
        binding.btnDay5.setOnClickListener {
            loc.day5=updataStatus(loc.day5)
            binding.btnDay5.setImageResource(setImageByStatus(loc.day5))

        }
        return binding.root
    }

    fun  bindingLocation(a: Location){
        binding.btnDay1.setImageResource(setImageByStatus(a.day1))
        binding.btnDay2.setImageResource(setImageByStatus(a.day2))
        binding.btnDay3.setImageResource(setImageByStatus(a.day3))
        binding.btnDay4.setImageResource(setImageByStatus(a.day4))
        binding.btnDay5.setImageResource(setImageByStatus(a.day5))
    }
  @RequiresApi(Build.VERSION_CODES.O)
  fun getAgenda(loc:Location):Agenda{
      val name = binding.nameUser.text.toString()
      val week1 = binding.weekUser.text.toString()
      var weekOfYear = 0
      val format = "dd/mm/yyyy"
      val df = SimpleDateFormat(format)
      val date: Date = df.parse(week1)
      val cal = Calendar.getInstance()
      cal.time = date
      weekOfYear = cal[Calendar.WEEK_OF_YEAR]
      var photo = binding.photoUser.text.toString();
      if(photo.isEmpty()){
          photo = "https://source.unsplash.com/1600x900/?avatar,person"
      }
      val email = binding.mailUser.text.toString();
      val phone = binding.phoneUser.text.toString();
      val fb = binding.fbUser.text.toString()
    return Agenda(0,name,photo,weekOfYear, loc, Contact(email,phone,fb))

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

    fun setImageByStatus(location: String):Int{
        var path=0
        when (location) {
            Status.OFF.name -> {
                path= R.drawable.vacation
            }
            Status.HOME.name -> {
                path=R.drawable.homepage
            }
            Status.WORK.name -> {
                path= R.drawable.work

            }
            else -> {
                path= R.drawable.homepage
            }
        }
        return  path
}
}
