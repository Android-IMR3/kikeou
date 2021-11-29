package fr.enssat.kikeou.alnezami_dansay.view.readQR

import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import fr.enssat.kikeou.alnezami_dansay.R
import fr.enssat.kikeou.alnezami_dansay.databinding.FragmentFormNewContactBinding
import fr.enssat.kikeou.alnezami_dansay.model.entity.Agenda
import fr.enssat.kikeou.alnezami_dansay.model.entity.Contact
import fr.enssat.kikeou.alnezami_dansay.model.entity.Location
import fr.enssat.kikeou.alnezami_dansay.model.entity.Status
import fr.enssat.kikeou.alnezami_dansay.view.qr.QrViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.ChronoField


class FormNewContactFragment : Fragment() {
    private lateinit var binding: FragmentFormNewContactBinding
    @InternalCoroutinesApi
    private lateinit var contactViewModel: FormNewContactViewModel
    private val args: FormNewContactFragmentArgs by navArgs()


    @RequiresApi(Build.VERSION_CODES.N)
    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFormNewContactBinding.inflate(inflater, container, false)
        contactViewModel = ViewModelProvider(this).get(FormNewContactViewModel::class.java)
        val agenda = args.newContact
        binding(agenda)
        val btnsave= binding.btnSave
        btnsave.setOnClickListener {

            if(agenda.id==0){
               contactViewModel.addAgenda(agenda)
                Toast.makeText(context,"Added successfully", Toast.LENGTH_SHORT).show()
            }else{
                contactViewModel.updateAgenda(agenda)
                Toast.makeText(context,"Updated successfully", Toast.LENGTH_SHORT).show()

            }

            findNavController().navigate(R.id.action_formNewContactFragment_to_listFragment)
        }





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

        val start = week.with(DayOfWeek.MONDAY)
        binding.weekHome2.setText("Week number "+a.week.toString()+" of "+start)
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
            Status.OFF.name -> {
                R.drawable.vacation
            }
            Status.HOME.name -> {
                R.drawable.homepage
            }
            Status.WORK.name -> {
                R.drawable.work

            }
            else -> {
                R.drawable.homepage
            }
        }


        return  path
    }




}