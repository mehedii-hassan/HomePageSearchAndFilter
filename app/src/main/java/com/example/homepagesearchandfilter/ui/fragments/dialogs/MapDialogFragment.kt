package com.example.homepagesearchandfilter.ui.fragments.dialogs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homepagesearchandfilter.R
import com.example.homepagesearchandfilter.adapters.MapItemsAdapter
import com.example.homepagesearchandfilter.adapters.ViewPagerAdapter
import com.example.homepagesearchandfilter.databinding.FragmentMapDialogBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator


class MapDialogFragment : BottomSheetDialogFragment() {


    private lateinit var binding: FragmentMapDialogBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // initialization
        binding = FragmentMapDialogBinding.inflate(inflater, container, false)

        binding.llSearch.setOnClickListener {

            // Navigation.findNavController(it).navigate(R.id.filterFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val adapter = ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "All"
                }
                1 -> {
                    tab.text = "Hair"
                }
                2 -> {
                    tab.text = "Nails Care"
                }
                3 -> {
                    tab.text = "Shampo"
                }
                4 -> {
                    tab.text = "All"
                }
            }
        }.attach()
    }
}


