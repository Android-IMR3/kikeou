package fr.enssat.kikeou.alnezami_dansay.view.home


import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import fr.enssat.kikeou.alnezami_dansay.R
import fr.enssat.kikeou.alnezami_dansay.databinding.FragmentUpdateBinding
import fr.enssat.kikeou.alnezami_dansay.model.entity.*
import fr.enssat.kikeou.alnezami_dansay.model.validations.validateAgenda
import kotlinx.coroutines.InternalCoroutinesApi
import java.text.ParseException
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
            val agenda=getAgenda(locList)
            var validationResult = validateAgenda(agenda)
            if(validationResult.errors.size>0){
                for(i in validationResult.errors){
                    Toast.makeText(context,i.message, Toast.LENGTH_SHORT).show()
                }
            }else{
                updateViewModel.updateMyProfile(agenda)
                Thread.sleep(2_000)
                findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
            }


        }

        binding.locations.btnDay1.setOnClickListener {
            locList.get(0).value=updataStatus( locList.get(0).value)
            binding.locations.btnDay1.setImageResource(setImageByStatus( locList.get(0).value))

        }
        binding.locations.btnDay2.setOnClickListener {
            locList.get(1).value= updataStatus( locList.get(1).value)
            binding.locations.btnDay2.setImageResource(setImageByStatus( locList.get(1).value))

        }
        binding.locations.btnDay3.setOnClickListener {
            locList.get(2).value= updataStatus( locList.get(2).value)
            binding.locations.btnDay3.setImageResource(setImageByStatus( locList.get(2).value))

        }
        binding.locations.btnDay4.setOnClickListener {
            locList.get(3).value = updataStatus( locList.get(3).value)
            binding.locations.btnDay4.setImageResource(setImageByStatus( locList.get(3).value))
        }
        binding.locations.btnDay5.setOnClickListener {
            locList.get(4).value=updataStatus( locList.get(4).value)
            binding.locations.btnDay5.setImageResource(setImageByStatus( locList.get(4).value))

        }




        val btnList = binding.navBottom.btnList
        btnList.setOnClickListener{_ ->
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        val btnGenerate = binding.navBottom.btnGenerate
        btnGenerate.setOnClickListener{_ ->
            findNavController().navigate(R.id.action_updateFragment_to_qrFragment)
        }
        val btnScanner = binding.navBottom.btnScanner
        btnScanner.setOnClickListener{_ ->
            findNavController().navigate(R.id.action_updateFragment_to_scannerQrFragment)
        }
        val btnHome = binding.navBottom.homeBtn
        btnHome.setOnClickListener{_ ->
             findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
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
        binding.locations.btnDay1.setImageResource(setImageByStatus(a.loc.get(0).value))
        binding.locations.btnDay2.setImageResource(setImageByStatus(a.loc.get(1).value))
        binding.locations.btnDay3.setImageResource(setImageByStatus(a.loc.get(2).value))
        binding.locations.btnDay4.setImageResource(setImageByStatus(a.loc.get(3).value))
        binding.locations.btnDay5.setImageResource(setImageByStatus(a.loc.get(4).value))
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun getAgenda(loc: List<LOC>):Agenda{
        val name = binding.nameUser.text.toString();
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
        val email = binding.emailUser.text.toString();
        val phone = binding.phoneUser.text.toString();
        val fb = binding.fbUser.text.toString()
        var l = listOf(Contact("email",email),Contact("phone",phone),Contact("FaceBook",fb))
        return Agenda(0,name,photo,l,weekOfYear.toLong(),loc)
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
         when(loc){
            Status.OFF.name,Status.Off.name -> {
                res = Status.HOME.name
                Toast.makeText(context,Status.HOME.name, Toast.LENGTH_SHORT).show()
            }
            Status.HOME.name,Status.teletravail.name -> {
                res = Status.WORK.name
                Toast.makeText(context,Status.WORK.name, Toast.LENGTH_SHORT).show()
            }
            Status.WORK.name,"WF 036" -> {
               res = Status.OFF.name
                Toast.makeText(context,Status.OFF.name, Toast.LENGTH_SHORT).show()
            }
            else -> {
               res = Status.WORK.name
                Toast.makeText(context,Status.WORK.name, Toast.LENGTH_SHORT).show()
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