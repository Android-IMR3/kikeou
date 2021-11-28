package fr.enssat.kikeou.alnezami_dansay.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import fr.enssat.kikeou.alnezami_dansay.R
import fr.enssat.kikeou.alnezami_dansay.databinding.FragmentHomeBinding
import fr.enssat.kikeou.alnezami_dansay.model.entity.Agenda
import kotlinx.coroutines.InternalCoroutinesApi
import android.graphics.BitmapFactory

import android.graphics.Bitmap





class HomeFragment : Fragment() {
    @InternalCoroutinesApi
    private lateinit var homeViewModel: HomeViewModel

    private lateinit var binding: FragmentHomeBinding

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.myAcount.observe(viewLifecycleOwner, Observer{agenda ->
            binding(agenda)
            val btnUpdate = binding.btnUpdateProfil
            btnUpdate.setOnClickListener{
                // Toast.makeText(context, "Update", Toast.LENGTH_SHORT).show()
                //  findNavController().navigate(R.id.action_homeFragment_to_updateFragment)
                val action = agenda?.let {
                    HomeFragmentDirections.actionHomeFragmentToUpdateFragment(
                        it
                    )
                }
                action?.let { findNavController().navigate(it) }

            }
        })




            return binding.root
    }
    fun binding(a: Agenda){
        binding.nameUser.setText(a.name);
        binding.emailUser.setText(a.contact.mail)
        binding.phoneUser.setText(a.contact.tel)
        binding.fbUser.setText(a.contact.fb)
        Picasso.get().load(a.photo).into(binding.appBarImage)
      /*  var bitmap = BitmapFactory.decodeFile(setImageByStatus(a.loc.day1))
        binding.day1.setTag(1,a.loc.day1)
        binding.day1.setImageBitmap(bitmap)
        bitmap = BitmapFactory.decodeFile(setImageByStatus(a.loc.day2))
        binding.day1.setTag(1,a.loc.day2)
        binding.day2.setImageBitmap(bitmap)
        bitmap = BitmapFactory.decodeFile(setImageByStatus(a.loc.day3))
        binding.day1.setTag(1,a.loc.day3)
        binding.day3.setImageBitmap(bitmap)
        bitmap = BitmapFactory.decodeFile(setImageByStatus(a.loc.day4))
        binding.day1.setTag(1,a.loc.day4)
        binding.day4.setImageBitmap(bitmap)
        bitmap = BitmapFactory.decodeFile(setImageByStatus(a.loc.day5))
        binding.day1.setTag(1,a.loc.day5)
        binding.day5.setImageBitmap(bitmap)*/
    }
    fun setImageByStatus(location: String):String{
        var path=""
        when (location) {
            "off" -> {
                    path= "src/main/res/mipmap-xhdpi/icon11.png"
            }
            "home" -> {
                path= "src/main/res/mipmap-xhdpi/icon14.png"
            }
            "work" -> {
                path= "src/main/res/mipmap-xhdpi/icon13.png"

            }
            else -> {
                path= "src/main/res/mipmap-xhdpi/icon13.png"
            }
        }

        return  path
    }
}