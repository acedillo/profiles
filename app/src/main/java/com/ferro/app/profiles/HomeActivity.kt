package com.ferro.app.profiles

import android.content.Intent
import android.os.Bundle
import com.ferro.app.profiles.common.MapActivity
import com.ferro.app.profiles.settings.SettingsActivity
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
