package com.ferro.app.profiles

import android.content.Intent
import com.ferro.app.profiles.common.MapActivity
import com.ferro.app.profiles.settings.SettingsActivity
import com.google.android.gms.maps.SupportMapFragment
import ferro.places.com.profiles.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : MapActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun setup() {

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.mMap) as SupportMapFragment
        mapFragment.getMapAsync(this)

        supportActionBar?.setTitle(R.string.title_activity_maps)

        mAddLocationButton.setOnClickListener { _ ->
            val intent : Intent = Intent(this@HomeActivity, SettingsActivity::class.java)
            startActivity(intent)
        }

    }


}
