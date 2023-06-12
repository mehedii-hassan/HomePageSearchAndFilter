package com.example.homepagesearchandfilter.ui.fragments.mapitemfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import com.example.homepagesearchandfilter.R
import com.example.homepagesearchandfilter.databinding.FragmentAllBinding
import com.example.homepagesearchandfilter.databinding.FragmentMapDialogBinding
import com.example.homepagesearchandfilter.utils.LocationPermissionService
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class AllFragment : Fragment(), OnMapReadyCallback {


    private lateinit var binding: FragmentAllBinding
    private lateinit var mMap: GoogleMap



        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            binding = FragmentAllBinding.inflate(layoutInflater, container, false)

            val mapFragment = childFragmentManager
                .findFragmentById(R.id.googleMapId) as SupportMapFragment
            mapFragment.getMapAsync(this)

            return binding.root
        }

        override fun onMapReady(googleMap: GoogleMap) {
            mMap = googleMap

            // Add a marker in Sydney and move the camera
            val latLng = LatLng(23.77433119, 90.3615925)
            mMap.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title("LatLong")
            )
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))


        }

        fun checkLocationPermission() {
            if (LocationPermissionService.isLocationPermissionGranted(requireContext())) {
                //detect user location
                //detectUserLocation()
            } else {
                //LocationPermissionService.requestLocationPermission(launcher);
            }
        }


        /*private ActivityResultLauncher<String> launcher =
                registerForActivityResult(new ActivityResultContracts.RequestPermission(), result -> {
                    if (result) {
                        //detect user location
                        detectUserLocation();
                    } else {
                        //show dialog and explain why you need this permission
                    }
                });*/

    }