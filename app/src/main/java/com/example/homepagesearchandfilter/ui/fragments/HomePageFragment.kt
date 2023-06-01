package com.example.homepagesearchandfilter.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.homepagesearchandfilter.R
import com.example.homepagesearchandfilter.databinding.FragmentHomePageBinding

class HomePageFragment : Fragment() {

    private lateinit var binding: FragmentHomePageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //initialization
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.clLocation.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.selectLocationFragment)
        }

        binding.llSearch.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.mapFragment)
        }
    }


}