package com.example.homepagesearchandfilter.ui.fragments.mapitemfragments


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.example.homepagesearchandfilter.R
import com.example.homepagesearchandfilter.databinding.FragmentNailsCareBinding
import com.example.homepagesearchandfilter.utils.LocationPermissionService
import com.example.homepagesearchandfilter.utils.MapMarkerIcon
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class NailsCareFragment : Fragment(), OnMapReadyCallback {


    private lateinit var binding: FragmentNailsCareBinding
    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationList: ArrayList<LatLng>
    private lateinit var markerIconList: ArrayList<BitmapDescriptor>
    private lateinit var mapMarkerIcon: MapMarkerIcon

    private val launcher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            if (result) {

            } else {
                //show dialog and explain why you need this permission

            }
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNailsCareBinding.inflate(layoutInflater, container, false)


        //initialization----------------------------------
        locationList = ArrayList()
        markerIconList = ArrayList()
        mapMarkerIcon = MapMarkerIcon(requireContext())
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.googleMapId) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return binding.root
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        //adding marker icon to a list--------------------------------
        markerIconList.add(mapMarkerIcon.markerIcon(R.drawable.location))
        markerIconList.add(mapMarkerIcon.markerIcon(R.drawable.marker_icon))
        markerIconList.add(mapMarkerIcon.markerIcon(R.drawable.marker_icon_one))
        markerIconList.add(mapMarkerIcon.markerIcon(R.drawable.marker_icon))
        markerIconList.add(mapMarkerIcon.markerIcon(R.drawable.marker_icon_one))
        markerIconList.add(mapMarkerIcon.markerIcon(R.drawable.marker_icon))


        /*googleMap.clear()
        googleMap.uiSettings.isZoomControlsEnabled = true*/

        if (LocationPermissionService.isLocationPermissionGranted(requireContext())) {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    val currentLatLng = LatLng(location.latitude, location.longitude)

                    locationList.add(currentLatLng)
                    locationList.add(LatLng(23.813334, 90.4242))
                    locationList.add(LatLng(23.797911, 90.414391))
                    locationList.add(LatLng(23.7980911, 90.414401))
                    locationList.add(LatLng(23.797921, 90.434391))
                    locationList.add(LatLng(23.797901, 90.454391))

                    for (l in locationList.indices) {
                        Log.e("TAG", "l " + l + "" + locationList[l])
                        mMap.addMarker(
                            MarkerOptions().position(locationList[l]).title("Marker")
                                .icon(markerIconList[l])
                        )
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationList[l], 13f))
                    }
                }
            }


        } else {
            LocationPermissionService.requestLocationPermission(launcher)
        }
    }

}