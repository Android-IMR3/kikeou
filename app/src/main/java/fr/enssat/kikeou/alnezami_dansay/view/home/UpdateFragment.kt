package fr.enssat.kikeou.alnezami_dansay.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import fr.enssat.kikeou.alnezami_dansay.R

import fr.enssat.kikeou.alnezami_dansay.databinding.FragmentUpdateBinding
import fr.enssat.kikeou.alnezami_dansay.model.entity.Agenda
import fr.enssat.kikeou.alnezami_dansay.model.entity.Contact
import fr.enssat.kikeou.alnezami_dansay.model.entity.Location
import fr.enssat.kikeou.alnezami_dansay.view.readQR.FormNewContactViewModel
import kotlinx.coroutines.InternalCoroutinesApi


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
        val btnUpdate = binding.btnUpdateProfile
        btnUpdate.setOnClickListener {
            updateViewModel.updateMyProfile(getAgenda())
           findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
        }
        return binding.root
    }
    fun  binding(a: Agenda){
        binding.nameUser.setText(a.name);
        binding.emailUser.setText(a.contact.mail)
        binding.phoneUser.setText(a.contact.tel)
        binding.fbUser.setText(a.contact.fb)
        binding.weekUser.setText(a.week.toString())
        binding.photoUser.setText(a.photo)
    }
    fun getAgenda():Agenda{
        val name = binding.nameUser.text.toString();
        val week1 = binding.weekUser.text.toString()
        var week:Int
        week=1
        try{
            week =  week1.toInt();
        }catch(e: Exception) {

        }
        var photo = binding.photoUser.text.toString();
        if(photo.isEmpty()){
            photo = "https://source.unsplash.com/1600x900/?avatar,person"
        }
        val email = binding.emailUser.text.toString();
        val phone = binding.phoneUser.text.toString();
        val fb = binding.fbUser.text.toString()


        val loc = Location("day1","day2","day3","day4","day5")
       // val loc = binding.locUser.text.toString()
        return Agenda(0,name,photo,week,args.myProfile.loc, Contact(email,phone,fb))

    }
    fun getStatusByImage(img :String):String{
        return ""
    }




}