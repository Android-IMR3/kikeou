package fr.enssat.kikeou.alnezami_dansay.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import fr.enssat.kikeou.alnezami_dansay.databinding.FragmentListBinding
import fr.enssat.kikeou.alnezami_dansay.model.entity.Agenda
import fr.enssat.kikeou.alnezami_dansay.model.entity.Contact
import fr.enssat.kikeou.alnezami_dansay.model.entity.Location


import kotlinx.coroutines.InternalCoroutinesApi







class ListPersonFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
        @InternalCoroutinesApi
        private lateinit var mPersonViewModel: ListPersonViewModel
    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        // Inflate view and obtain an instance of the binding class

        val view = binding.root
        //recyclerview
        var adapter = PersonAdapter()
        val recyclerview = binding.recyclerview
        recyclerview.adapter= adapter
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        //





       // mPersonViewModel = ViewModelProvider.AndroidViewModelFactory(this.activity!!.application).create(ListPersonViewModal::class.java)

       mPersonViewModel = ViewModelProvider(this).get(ListPersonViewModel::class.java)
        var contacts = Contact("ibrahim","345678","test@fb")

        var loc = Location(1,"OFF")
        var person = Agenda(0,"ibrahim","https://picsum.photos/200/300?random=1",34,loc,contacts)
        mPersonViewModel.addPerson(person);
       // mPersonViewModel.readAllData.observe(viewLifecycleOwner, Observer{user -> adapter.setData(user)})

        val btnGenerate = binding.navBottom.btnGenerate
        btnGenerate.setOnClickListener{_ ->
            findNavController().navigate(fr.enssat.kikeou.alnezami_dansay.R.id.action_listFragment_to_qrFragment)
        }

        val btnScanner = binding.navBottom.btnScanner
        btnScanner.setOnClickListener{_ ->
            findNavController().navigate(fr.enssat.kikeou.alnezami_dansay.R.id.action_listFragment_to_scannerQrFragment)

        }


        val btnHome = binding.navBottom.homeBtn
        btnHome.setOnClickListener{_ ->
            findNavController().navigate(fr.enssat.kikeou.alnezami_dansay.R.id.action_listFragment_to_homeFragment)

        }
       return view
    }

}