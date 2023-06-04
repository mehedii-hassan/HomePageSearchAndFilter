package com.example.homepagesearchandfilter.ui.fragments.dialogs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.homepagesearchandfilter.R
import com.example.homepagesearchandfilter.databinding.FragmentMapDialogBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class MapDialogFragment : BottomSheetDialogFragment(), OnMapReadyCallback {


    private lateinit var binding: FragmentMapDialogBinding
    private lateinit var mMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // initialization
        binding = FragmentMapDialogBinding.inflate(inflater, container, false)
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.googleMapId) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.llSearch.setOnClickListener {

            // Navigation.findNavController(it).navigate(R.id.filterFragment)
        }
        return binding.root
    }


    override fun onMapReady(googleMap: GoogleMap) {


        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(
            MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))


    }

}