package fr.enssat.kikeou.alnezami_dansay.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.arch.core.util.Function
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
import kotlinx.coroutines.flow.count
import androidx.lifecycle.Transformations





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

        mPersonViewModel.allAgendas.observe(viewLifecycleOwner, Observer{user -> adapter.setData(user)})
        mPersonViewModel.count.observe(viewLifecycleOwner, Observer{count -> if(count==0){
            findNavController().navigate(fr.enssat.kikeou.alnezami_dansay.R.id.action_listFragment_to_registerFragment)
        } })


        val btnList = binding.navBottom.btnList
        btnList.setOnClickListener{_ ->
            //  val msg =  mPersonViewModel.count.value.toString()
            // Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()

          //  findNavController().navigate(fr.enssat.kikeou.alnezami_dansay.R.id.action_listFragment_to_qrFragment)
        }

        val btnGenerate = binding.navBottom.btnGenerate
        btnGenerate.setOnClickListener{_ ->
          //  val msg =  mPersonViewModel.count.value.toString()
           // Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()

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