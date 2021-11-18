package fr.enssat.kikeou.alnezami_dansay.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import fr.enssat.kikeou.alnezami_dansay.database.entity.Person
import fr.enssat.kikeou.alnezami_dansay.databinding.FragmentListBinding
import kotlinx.coroutines.InternalCoroutinesApi


class ListPersonFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
        @InternalCoroutinesApi
        private lateinit var mPersonViewModel: ListPersonViewModal
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

       mPersonViewModel = ViewModelProvider(this).get(ListPersonViewModal::class.java)
        var person = Person(0,"ibrahim","alnezami","balaba",66,"0987654323","ibra@ibra.com","lannion")
        mPersonViewModel.addPerson(person);
        mPersonViewModel.readAllData.observe(viewLifecycleOwner, Observer{user -> adapter.setData(user)})


       return view
    }

}