package fr.enssat.kikeou.alnezami_dansay.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import fr.enssat.kikeou.alnezami_dansay.R
import fr.enssat.kikeou.alnezami_dansay.databinding.FragmentRegisterBinding
import kotlinx.coroutines.InternalCoroutinesApi


import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.location.Location
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import fr.enssat.kikeou.alnezami_dansay.model.entity.*
import fr.enssat.kikeou.alnezami_dansay.model.validations.validateAgenda
import java.text.ParseException
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
       // binding.circularProgressLayout.setVisibility(View.INVISIBLE)
        var locList =listOf(LOC(),LOC(),LOC(),LOC(),LOC())

        bindingLocation(locList)
        var agenda: Agenda
        val btn = binding.btnSignup
        btn.setOnClickListener {


                agenda = getAgenda(locList)
                var validationResult = validateAgenda(agenda)
                if(validationResult.errors.size>0){
                    for(i in validationResult.errors){
                        Toast.makeText(context,i.message,Toast.LENGTH_SHORT).show()
                    }
                }else{
                    registerViewModel.signUp(agenda)
                    //  binding.circularProgressLayout.setVisibility(View.VISIBLE)
                    Thread.sleep(2_000)
                    //   binding.circularProgressLayout.setVisibility(View.INVISIBLE)
                    findNavController().navigate(R.id.action_registerFragment_to_listFragment)
                }


        }

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
        return binding.root
    }

    fun  bindingLocation(a: List<LOC>){
        binding.btnDay1.setImageResource(setImageByStatus(a.get(0).value))
        binding.btnDay2.setImageResource(setImageByStatus(a.get(1).value))
        binding.btnDay3.setImageResource(setImageByStatus(a.get(2).value))
        binding.btnDay4.setImageResource(setImageByStatus(a.get(3).value))
        binding.btnDay5.setImageResource(setImageByStatus(a.get(4).value))
    }
  @RequiresApi(Build.VERSION_CODES.O)
  fun getAgenda(loc: List<LOC>):Agenda{
      val name = binding.nameUser.text.toString()
      val week1 = binding.weekUser.text.toString()
      var weekOfYear=-1
      try {
           weekOfYear  = getdateformated(week1)
      }catch(e:ParseException){
          Log.e("register", "error format")
      }
      var photo = binding.photoUser.text.toString();
      if(photo.isEmpty()){
          photo = "https://source.unsplash.com/1600x900/?avatar,person"
      }
      val email = binding.mailUser.text.toString();
      val phone = binding.phoneUser.text.toString();
      val fb = binding.fbUser.text.toString()
      var l = listOf(Contact("email",email),Contact("phone",phone),Contact("FaceBook",fb))

      return Agenda(0,name,photo, l,weekOfYear.toLong(),loc )

  }

    @RequiresApi(Build.VERSION_CODES.N)
    @Throws(ParseException::class)
    fun getdateformated(w: String):Int{
        var weekOfYear = 0
        val format = "dd/mm/yyyy"
        val df = SimpleDateFormat(format)
        val date: Date = df.parse(w)
        val cal = Calendar.getInstance()
        cal.time = date
        weekOfYear = cal[Calendar.WEEK_OF_YEAR]
        return weekOfYear
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
