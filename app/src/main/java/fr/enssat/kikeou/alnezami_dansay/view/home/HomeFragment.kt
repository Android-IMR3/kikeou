package fr.enssat.kikeou.alnezami_dansay.view.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import fr.enssat.kikeou.alnezami_dansay.R
import fr.enssat.kikeou.alnezami_dansay.databinding.FragmentHomeBinding
import kotlinx.coroutines.InternalCoroutinesApi

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import fr.enssat.kikeou.alnezami_dansay.model.entity.Status
import java.time.LocalDate

import java.time.DayOfWeek

import java.time.temporal.ChronoField





class HomeFragment : Fragment() {
    @InternalCoroutinesApi
    private lateinit var homeViewModel: HomeViewModel

    private lateinit var binding: FragmentHomeBinding

    @RequiresApi(Build.VERSION_CODES.N)
    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.myAcount.observe(viewLifecycleOwner, Observer{agenda ->
            binding(agenda)
            val btnUpdate = binding.btnUpdateProfil
            btnUpdate.setOnClickListener{

                val action = agenda?.let {
                    HomeFragmentDirections.actionHomeFragmentToUpdateFragment(
                        it
                    )
                }
                action?.let { findNavController().navigate(it) }

            }
            var btnCall =binding.phoneUser
            btnCall.setOnClickListener{
                call()
            }
            val btnEmail = binding.emailUser
            btnEmail.setOnClickListener{
                openMail()
            }
        })




            return binding.root
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun binding(a: Agenda){
        binding.nameUser.setText(a.name);
        binding.emailUser.setText(a.contact.mail)
        binding.phoneUser.setText(a.contact.tel)
        binding.fbUser.setText(a.contact.fb)

        Picasso.get().load(a.photo).into(binding.appBarImage)
        binding.btnDay1.setImageResource(setImageByStatus(a.loc.day1))
        binding.btnDay2.setImageResource(setImageByStatus(a.loc.day2))
        binding.btnDay3.setImageResource(setImageByStatus(a.loc.day3))
        binding.btnDay4.setImageResource(setImageByStatus(a.loc.day4))
        binding.btnDay5.setImageResource(setImageByStatus(a.loc.day5))
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
        binding.weekHome.setText("Week number "+a.week.toString()+" of "+start)

    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun getDayStatus(location: Location):Int{
        val calendar: Calendar = Calendar.getInstance()
        val day: Int = calendar.get(Calendar.DAY_OF_WEEK)
        var path = 0
        path = when (day) {
            Calendar.MONDAY -> {
                setImageByStatus(location.day1)
            }
            Calendar.TUESDAY -> {
                setImageByStatus(location.day2)
            }
            Calendar.WEDNESDAY -> {
                setImageByStatus(location.day3)
            }
            Calendar.THURSDAY -> {
                setImageByStatus(location.day4)
            }
            Calendar.FRIDAY -> {
                setImageByStatus(location.day5)
            }
            else ->   setImageByStatus(location.day5)
        }
        return path
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
    fun call() {
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:" + binding.phoneUser.text )
        startActivity(dialIntent)
    }
    fun openMail(){
        val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts(
            "mailto",binding.emailUser.text.toString(), null));
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }
}