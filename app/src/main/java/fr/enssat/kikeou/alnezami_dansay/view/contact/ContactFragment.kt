package fr.enssat.kikeou.alnezami_dansay.view.contact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import fr.enssat.kikeou.alnezami_dansay.R
import fr.enssat.kikeou.alnezami_dansay.databinding.FragmentContactBinding
import fr.enssat.kikeou.alnezami_dansay.databinding.FragmentFormNewContactBinding
import fr.enssat.kikeou.alnezami_dansay.model.entity.Agenda
import fr.enssat.kikeou.alnezami_dansay.view.readQR.FormNewContactFragmentArgs
import fr.enssat.kikeou.alnezami_dansay.view.readQR.FormNewContactViewModel
import fr.enssat.kikeou.alnezami_dansay.view.readQR.ScannerQrFragmentDirections
import kotlinx.coroutines.InternalCoroutinesApi


class ContactFragment : Fragment() {
    private lateinit var binding: FragmentContactBinding
    @InternalCoroutinesApi
    private lateinit var contactViewModel: ContactViewModel
    private val args: ContactFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactBinding.inflate(inflater, container, false)
        val agenda = args.contact;
        binding(agenda)

        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        val btnUpdate = binding.btnUpdate
        btnUpdate.setOnClickListener {_ ->
            val action = agenda?.let {
                ContactFragmentDirections.actionContactFragmentToScannerQrFragment(
                    it
                )
            }
            action?.let { findNavController().navigate(it) }

        }
        val btnDel = binding.btnDelete
        btnDel.setOnClickListener {_ ->
            contactViewModel.deleteAgenda(agenda)
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