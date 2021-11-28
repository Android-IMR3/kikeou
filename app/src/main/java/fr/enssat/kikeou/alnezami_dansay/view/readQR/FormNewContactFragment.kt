package fr.enssat.kikeou.alnezami_dansay.view.readQR

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import fr.enssat.kikeou.alnezami_dansay.R
import fr.enssat.kikeou.alnezami_dansay.databinding.FragmentFormNewContactBinding
import fr.enssat.kikeou.alnezami_dansay.model.entity.Agenda
import fr.enssat.kikeou.alnezami_dansay.model.entity.Contact
import fr.enssat.kikeou.alnezami_dansay.model.entity.Location
import fr.enssat.kikeou.alnezami_dansay.view.qr.QrViewModel
import kotlinx.coroutines.InternalCoroutinesApi


class FormNewContactFragment : Fragment() {
    private lateinit var binding: FragmentFormNewContactBinding
    @InternalCoroutinesApi
    private lateinit var contactViewModel: FormNewContactViewModel
    private val args: FormNewContactFragmentArgs by navArgs()


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
    fun binding(a: Agenda){
        binding.nameUser.setText(a.name);
        binding.emailUser.setText(a.contact.mail)
        binding.phoneUser.setText(a.contact.tel)
        binding.fbUser.setText(a.contact.fb)
        Picasso.get().load(a.photo).into(binding.appBarImage)
    }




}