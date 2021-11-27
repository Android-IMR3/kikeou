package fr.enssat.kikeou.alnezami_dansay.view.contact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import fr.enssat.kikeou.alnezami_dansay.R
import fr.enssat.kikeou.alnezami_dansay.databinding.FragmentContactBinding
import fr.enssat.kikeou.alnezami_dansay.databinding.FragmentFormNewContactBinding
import fr.enssat.kikeou.alnezami_dansay.view.readQR.FormNewContactFragmentArgs
import fr.enssat.kikeou.alnezami_dansay.view.readQR.FormNewContactViewModel
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
        binding.nameUser.setText(args.contact.name)
        binding.adresseUser.setText(args.contact.contact.mail)
        binding.phoneUser.setText(args.contact.contact.tel)

        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)

       return binding.root
    }


}