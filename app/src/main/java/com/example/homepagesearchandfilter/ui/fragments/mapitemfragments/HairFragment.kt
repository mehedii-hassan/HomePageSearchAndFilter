package com.example.homepagesearchandfilter.ui.fragments.mapitemfragments

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.homepagesearchandfilter.R
import com.example.homepagesearchandfilter.databinding.FragmentHairBinding
import com.example.homepagesearchandfilter.utils.LocationPermissionService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class HairFragment : Fragment(), OnMapReadyCallback {


    private lateinit var binding: FragmentHairBinding
    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

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
        binding = FragmentHairBinding.inflate(layoutInflater, container, false)

        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())


        val mapFragment = childFragmentManager
            .findFragmentById(R.id.googleMapId) as SupportMapFragment
        mapFragment.getMapAsync(this)
        //checkLocationPermission()

        return binding.root
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Create a Drawable object from the desired drawable icon
        val drawableIcon: Drawable? =
            ContextCompat.getDrawable(requireContext(), R.drawable.location)
        // Convert the Drawable to a Bitmap
        val bitmapIcon: Bitmap = drawableIcon?.let {
            val canvas = Canvas()
            val bitmap =
                Bitmap.createBitmap(it.intrinsicWidth, it.intrinsicHeight, Bitmap.Config.ARGB_8888)
            canvas.setBitmap(bitmap)
            it.setBounds(0, 0, it.intrinsicWidth, it.intrinsicHeight)
            it.draw(canvas)
            bitmap
        } ?: Bitmap.createBitmap(
            1,
            1,
            Bitmap.Config.ARGB_8888
        )
        val markerIcon = BitmapDescriptorFactory.fromBitmap(bitmapIcon)


        if (LocationPermissionService.isLocationPermissionGranted(requireContext())) {
            //detect user location
            //detectUserLocation()
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
                    Log.e("TAG", "" + location.latitude)
                    mMap.addMarker(MarkerOptions().position(currentLatLng).icon(markerIcon))
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
                }
            }
        } else {
            LocationPermissionService.requestLocationPermission(launcher)
        }
    }

}



