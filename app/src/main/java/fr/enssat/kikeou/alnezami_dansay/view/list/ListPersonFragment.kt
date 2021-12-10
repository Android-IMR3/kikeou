package fr.enssat.kikeou.alnezami_dansay.view.list

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import fr.enssat.kikeou.alnezami_dansay.R
import fr.enssat.kikeou.alnezami_dansay.databinding.FragmentListBinding


import kotlinx.coroutines.InternalCoroutinesApi


class ListPersonFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    @InternalCoroutinesApi
    private lateinit var mPersonViewModel: ListPersonViewModel
    private var adapter = PersonAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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

        val recyclerview = binding.recyclerview
        recyclerview.adapter= adapter
        recyclerview.layoutManager = LinearLayoutManager(requireContext())

        mPersonViewModel = ViewModelProvider(this).get(ListPersonViewModel::class.java)

        mPersonViewModel.allAgendas.observe(viewLifecycleOwner, Observer{user -> adapter.setData(user)})
        //if no data in data base send user to register his data
        mPersonViewModel.count.observe(viewLifecycleOwner, Observer{count -> if(count==0){
            findNavController().navigate(fr.enssat.kikeou.alnezami_dansay.R.id.action_listFragment_to_registerFragment)
        } })
        val btnList = binding.navBottom.btnList
        btnList.setOnClickListener{_ ->
           // findNavController().navigate(R.id.action_listFragment_to_qrFragment)
        }
        val btnGenerate = binding.navBottom.btnGenerate
        btnGenerate.setOnClickListener{_ ->
              findNavController().navigate(R.id.action_listFragment_to_qrFragment)
        }
        val btnScanner = binding.navBottom.btnScanner
        btnScanner.setOnClickListener{_ ->
            findNavController().navigate(R.id.action_listFragment_to_scannerQrFragment)
        }
        val btnHome = binding.navBottom.homeBtn
        btnHome.setOnClickListener{_ ->
            findNavController().navigate(R.id.action_listFragment_to_homeFragment)
        }
       return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_menu,menu)
        val search : MenuItem? = menu?.findItem(R.id.app_bar_search)
        val searchView  : SearchView =search?.actionView as SearchView
        searchView.queryHint = "Search something"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {

                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                adapter?.filter?.filter(newText)
                if (newText != null) {
                    Log.i("search fragment",newText)
                }
                return true

            }
        })
        search.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                adapter?.filter?.filter("")

                Toast.makeText(context,"Action Collapse",Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {

                Toast.makeText(context,"Action Expand",Toast.LENGTH_SHORT).show()
                return true
            }
        })
        return super.onCreateOptionsMenu(menu!!, inflater!!)
    }


}