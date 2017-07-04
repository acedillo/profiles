package com.ferro.app.profiles.common

import android.Manifest
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.ferro.app.profiles.util.MapUtil
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback

/**
 * Created by Abraham on 6/20/2017.
 */
 abstract class MapActivity : BaseActivity(), OnMapReadyCallback,
         GoogleApiClient.OnConnectionFailedListener {

    protected val PERMISSIONS_REQUEST_FINE_LOCATION: Int = 0

    protected var mMap : GoogleMap? = null

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

        val permission: Int = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        if (permission == PackageManager.PERMISSION_GRANTED) {
            sendUserToCurrentLocation()
        }
        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSIONS_REQUEST_FINE_LOCATION)
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSIONS_REQUEST_FINE_LOCATION -> if (grantResults.isEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
               sendUserToCurrentLocation()
            }
        }
    }

    protected open fun sendUserToCurrentLocation(addMarker: Boolean = false, zoom: Float = 15f) {
        MapUtil.sentUserToCurrentLocation(this, mMap!!, addMarker)
    }
}