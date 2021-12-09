package fr.enssat.kikeou.alnezami_dansay.view.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.enssat.kikeou.alnezami_dansay.databinding.FragmentContactBinding
import fr.enssat.kikeou.alnezami_dansay.databinding.FragmentSearchBinding

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            // TO ADD
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.root;
    }
}