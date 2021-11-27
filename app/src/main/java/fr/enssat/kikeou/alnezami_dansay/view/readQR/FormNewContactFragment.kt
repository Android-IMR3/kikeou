package fr.enssat.kikeou.alnezami_dansay.view.readQR

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import fr.enssat.kikeou.alnezami_dansay.databinding.FragmentFormNewContactBinding
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
        binding.nameUser.setText(args.newContact.name)
        binding.adresseUser.setText(args.newContact.contact.tel)
        binding.phoneUser.setText(args.newContact.contact.mail)

        contactViewModel = ViewModelProvider(this).get(FormNewContactViewModel::class.java)
        contactViewModel.addPerson(args.newContact)
        return binding.root
    }



}