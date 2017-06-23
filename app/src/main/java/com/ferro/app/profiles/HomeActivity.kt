package com.ferro.app.profiles

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.ferro.app.profiles.common.MapActivity
import com.ferro.app.profiles.settings.SettingsActivity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import ferro.places.com.profiles.R
import kotlinx.android.synthetic.main.activity_maps.*

class HomeActivity : MapActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        setup()
    }

    private fun setup() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        setSupportActionBar(mToolbar)
        mToolbar.setTitle(R.string.title_activity_maps)

        addLocationButton.setOnClickListener { _ ->
            val intent : Intent = Intent(this@HomeActivity, SettingsActivity::class.java)
            startActivity(intent)
        }

    }


}
