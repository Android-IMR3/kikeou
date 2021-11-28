package fr.enssat.kikeou.alnezami_dansay.view.home

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
import kotlinx.coroutines.InternalCoroutinesApi


/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    @InternalCoroutinesApi
    private lateinit var registerViewModel: RegisterViewModel

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
   val btn = binding.btnSignup
        btn.setOnClickListener {
           val agenda = getAgenda()
            registerViewModel.signUp(agenda)
            findNavController().navigate(R.id.action_registerFragment_to_listFragment)
        }
        return binding.root
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
      val email = binding.mailUser.text.toString();
      val phone = binding.phoneUser.text.toString();
      val fb = binding.fbUser.text.toString()
      val loc = binding.locval.text.toString()
    return Agenda(0,name,photo,week, Location(), Contact(email,phone,fb))

  }

}