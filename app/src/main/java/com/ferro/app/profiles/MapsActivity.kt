package com.ferro.app.profiles

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ferro.places.com.profiles.R

class MapsActivity : FragmentActivity(), OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback, GoogleApiClient.OnConnectionFailedListener {


    private val PERMISSIONS_REQUEST_FINE_LOCATION: Int = 0
    private val DEFAULT_MAP_ZOOM: Float = 15f

    private var mMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        mMap!!.uiSettings.setAllGesturesEnabled(true)
        mMap!!.mapType = GoogleMap.MAP_TYPE_NORMAL

        val permission : Int = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        if(permission == PackageManager.PERMISSION_GRANTED) {
           sendUserToCurrentLocation()
        }
        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSIONS_REQUEST_FINE_LOCATION)

        mMap!!.setOnMapLongClickListener { latLng ->
            val markerOptions = MarkerOptions()
            markerOptions.position(latLng)
            mMap!!.addMarker(markerOptions)
        }

    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSIONS_REQUEST_FINE_LOCATION ->  if(grantResults.isEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                sendUserToCurrentLocation()
            }
        }
    }

    private fun sendUserToCurrentLocation() {
        var latLng : LatLng
        val locationClient = LocationServices.getFusedLocationProviderClient(this)
        mMap!!.isMyLocationEnabled = true
        locationClient.lastLocation.addOnSuccessListener(this, { location ->
            if(location != null) {

                latLng = LatLng(location.latitude, location.longitude)
                val cameraPosition = CameraPosition.Builder()
                        .zoom(DEFAULT_MAP_ZOOM)
                        .target(latLng)
                        .build()
                mMap!!.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
            }
        })
    }


}
