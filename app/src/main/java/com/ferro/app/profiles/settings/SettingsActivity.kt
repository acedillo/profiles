package com.ferro.app.profiles.settings

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.widget.SeekBar
import com.ferro.app.profiles.common.MapActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import ferro.places.com.profiles.R
import kotlinx.android.synthetic.main.activity_settings.*


class SettingsActivity : MapActivity(), SettingsFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(mToolbar)
        supportActionBar?.setTitle(R.string.settings_title)

        setup()
    }

    private fun setup() {
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        super.onMapReady(googleMap)
        var position : LatLng? = null
        var radius : Double
        var circle : Circle? = null
        val circleOptions: CircleOptions = CircleOptions()
                .radius(1.0)
                .strokeColor(R.color.colorPrimaryDark)
                .fillColor(R.color.colorPrimary)

        mMapCircleSizeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                //This should happen only before the user drag the marker

                if(position == null){
                    position = mMap!!.cameraPosition?.target
                }

                if(circle == null){
                    circleOptions.center(position)
                    circle = mMap!!.addCircle(circleOptions)
                }
                circle!!.center = position
                radius = progress.toDouble() * 2
                circle!!.radius = radius


            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                //NOP
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                //NOP
            }

        })
        mMap!!.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener {
            override fun onMarkerDragStart(p0: Marker?) {
               //NOP
            }

            override fun onMarkerDrag(p0: Marker?) {
                //NOP
            }

            override fun onMarkerDragEnd(marker: Marker?) {
                position = marker!!.position
                circle!!.center = position
                if(circle == null) {
                    circleOptions.center(position)
                    circle = mMap!!.addCircle(circleOptions)
                }
            }
        })


    }

    override fun sendUserToCurrentLocation(addMarker: Boolean, zoom: Float) {
        super.sendUserToCurrentLocation(true, 17f)
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
