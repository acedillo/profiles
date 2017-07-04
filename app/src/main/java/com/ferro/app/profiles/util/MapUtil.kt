package com.ferro.app.profiles.util

import android.app.Activity
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/**
 * Created by Abraham on 6/30/2017.
 */

class MapUtil{
    companion object{

        fun sentUserToCurrentLocation(activity: Activity, mMap : GoogleMap, addMarker: Boolean = false, zoom: Float = 15f){
            var latLng: LatLng
            val locationClient = LocationServices.getFusedLocationProviderClient(activity)
            mMap.isMyLocationEnabled = true
            locationClient.lastLocation.addOnSuccessListener(activity, { location ->
                if (location != null) {
                    latLng = LatLng(location.latitude, location.longitude)
                    val cameraPosition = CameraPosition.Builder()
                            .zoom(zoom)
                            .target(latLng)
                            .build()
                    mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
                    if(addMarker){
                        mMap.addMarker(MarkerOptions().position(latLng).draggable(true))
                    }
                }
            })
        }
    }
}