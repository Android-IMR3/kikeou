package fr.enssat.kikeou.alnezami_dansay.view.readQR

import android.icu.util.Calendar
import android.location.Location
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
import fr.enssat.kikeou.alnezami_dansay.model.entity.LOC
import fr.enssat.kikeou.alnezami_dansay.model.entity.Status
import fr.enssat.kikeou.alnezami_dansay.model.validations.validateAgenda
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


            var validationResult = validateAgenda(agenda)
            if(validationResult.errors.size>0){
                for(i in validationResult.errors){
                    Toast.makeText(context,i.message, Toast.LENGTH_SHORT).show()
                }
            }else{
                if(agenda.id==0){
                    contactViewModel.addAgenda(agenda)
                    Toast.makeText(context,"Added successfully", Toast.LENGTH_SHORT).show()
                }else{
                    contactViewModel.updateAgenda(agenda)
                    Toast.makeText(context,"Updated successfully", Toast.LENGTH_SHORT).show()

                }
                findNavController().navigate(R.id.action_formNewContactFragment_to_listFragment)
            }

        }

  val btnList = binding.navBottom.btnList
        btnList.setOnClickListener{_ ->
            findNavController().navigate(R.id.action_formNewContactFragment_to_listFragment)
        }
        val btnGenerate = binding.navBottom.btnGenerate
        btnGenerate.setOnClickListener{_ ->
             findNavController().navigate(R.id.action_formNewContactFragment_to_qrFragment)
        }
        val btnScanner = binding.navBottom.btnScanner
        btnScanner.setOnClickListener{_ ->
            findNavController().navigate(R.id.action_formNewContactFragment_to_scannerQrFragment)
        }
        val btnHome = binding.navBottom.homeBtn
        btnHome.setOnClickListener{_ ->
            findNavController().navigate(R.id.action_formNewContactFragment_to_homeFragment)

        }



        return binding.root
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun binding(a: Agenda){
        binding.nameUser.setText(a.name);

        try {
            if(a.contact.size==1)
                binding.emailUser.setText(a.contact.get(0).value)
        }catch( e: Exception ){
            binding.emailUser.setText("pas de email")
        }
        try {
            if(a.contact.size==2)
                binding.phoneUser.setText(a.contact.get(1).value)
        }catch( e: Exception ){
            binding.phoneUser.setText("pas de phone")
        }
        try{
            if(a.contact.size==3)
                binding.fbUser.setText(a.contact.get(2).value)
        }catch( e:Exception ){
            binding.fbUser.setText("pas de fb")
        }

        Picasso.get().load(a.photo).into(binding.appBarImage)
        try {
            if(a.loc.size==1){
                binding.btnDay1.setImageResource(setImageByStatus(a.loc.get(0).value))

            }else{
                a.loc.toMutableList().add(LOC())
            }
            if(a.loc.size==2){
                binding.btnDay2.setImageResource(setImageByStatus(a.loc.get(1).value))
            }else{
                a.loc.toMutableList().add(LOC())
            }
            if(a.loc.size==3){
                binding.btnDay3.setImageResource(setImageByStatus(a.loc.get(2).value))
            }else{
                a.loc.toMutableList().add(LOC())
            }
            if(a.loc.size==4){
                binding.btnDay4.setImageResource(setImageByStatus(a.loc.get(3).value))
            }else{
                a.loc.toMutableList().add(LOC())
            }
            if(a.loc.size==5){
                binding.btnDay5.setImageResource(setImageByStatus(a.loc.get(4).value))
            }else{
                a.loc.toMutableList().add(LOC())
            }

                binding.statusImg.setImageResource(getDayStatus(a.loc))
        }catch(e: Exception){
            Toast.makeText(context,"Pas de Locations",Toast.LENGTH_LONG).show()
        }

        val week = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDate.now().with(ChronoField.ALIGNED_WEEK_OF_YEAR, a.week.toLong())
        } else {
            TODO("VERSION.SDK_INT < O")
        }

        val start = week.with(DayOfWeek.MONDAY)
        binding.weekHome2.setText("Week number "+a.week.toString()+" of "+start)
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun getDayStatus(location: List<LOC>):Int{
        val calendar: Calendar = Calendar.getInstance()
        val day: Int = calendar.get(Calendar.DAY_OF_WEEK)
        var path = 0
        path = when (day) {
            Calendar.MONDAY -> {
                setImageByStatus(location.get(0).value)
            }
            Calendar.TUESDAY -> {
                setImageByStatus(location.get(1).value)
            }
            Calendar.WEDNESDAY -> {
                setImageByStatus(location.get(2).value)
            }
            Calendar.THURSDAY -> {
                setImageByStatus(location.get(3).value)
            }
            Calendar.FRIDAY -> {
                setImageByStatus(location.get(4).value)
            }
            else ->   setImageByStatus(location.get(0).value)
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