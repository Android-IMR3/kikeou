package fr.enssat.kikeou.alnezami_dansay.view.home

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.location.Location
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import fr.enssat.kikeou.alnezami_dansay.R

import fr.enssat.kikeou.alnezami_dansay.databinding.FragmentUpdateBinding
import fr.enssat.kikeou.alnezami_dansay.model.entity.*
import kotlinx.coroutines.InternalCoroutinesApi



import java.time.LocalDate
import java.time.temporal.ChronoField
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [UpdateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UpdateFragment : Fragment() {

    private val args: UpdateFragmentArgs by navArgs()

    @InternalCoroutinesApi
    private lateinit var updateViewModel: UpdateViewModel
    private lateinit var binding: FragmentUpdateBinding


    @RequiresApi(Build.VERSION_CODES.O)
    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpdateBinding.inflate(inflater, container, false)
        updateViewModel = ViewModelProvider(this).get(UpdateViewModel::class.java)

        val myprofile= args.myProfile
        binding(myprofile)
        var locList =myprofile.loc
        val btnUpdate = binding.btnUpdateProfile
        btnUpdate.setOnClickListener {
            updateViewModel.updateMyProfile(getAgenda(locList))
           findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
        }

        binding.btnDay1.setOnClickListener {
            locList.get(0).value=updataStatus( locList.get(0).value)
            binding.btnDay1.setImageResource(setImageByStatus( locList.get(0).value))

        }
        binding.btnDay2.setOnClickListener {
            locList.get(1).value= updataStatus( locList.get(1).value)
            binding.btnDay2.setImageResource(setImageByStatus( locList.get(1).value))

        }
        binding.btnDay3.setOnClickListener {
            locList.get(2).value= updataStatus( locList.get(2).value)
            binding.btnDay3.setImageResource(setImageByStatus( locList.get(2).value))

        }
        binding.btnDay4.setOnClickListener {
            locList.get(3).value = updataStatus( locList.get(3).value)
            binding.btnDay4.setImageResource(setImageByStatus( locList.get(3).value))
        }
        binding.btnDay5.setOnClickListener {
            locList.get(4).value=updataStatus( locList.get(4).value)
            binding.btnDay5.setImageResource(setImageByStatus( locList.get(4).value))

        }

        return binding.root
    }
    fun  binding(a: Agenda){
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
    @RequiresApi(Build.VERSION_CODES.O)
    fun getAgenda(loc: List<LOC>):Agenda{
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


        return Agenda(0,name,photo,l,weekOfYear.toLong(),loc)

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
        path = when (location) {
            Status.OFF.name,Status.Off.name -> {
                R.drawable.vacation
            }
            Status.HOME.name,Status.teletravail.name -> {
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




}