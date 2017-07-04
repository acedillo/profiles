package com.ferro.app.profiles

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.ferro.app.profiles.common.MapActivity
import com.ferro.app.profiles.settings.MODE_BLUETOOTH
import com.ferro.app.profiles.settings.MODE_SETTINGS
import com.ferro.app.profiles.settings.MapSettingsActivity
import com.ferro.app.profiles.settings.SettingsActivity
import com.google.android.gms.maps.SupportMapFragment
import ferro.places.com.profiles.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : MapActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun getMenuId(): Int {
        return R.menu.main_settings
    }

    override fun getTitleId(): Int {
        return R.string.app_name
    }

    override fun setup(savedInstanceState: Bundle?) {

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.mMap) as SupportMapFragment
        mapFragment.getMapAsync(this)

        mAddLocationButton.setOnClickListener { _ ->
            val intent : Intent = Intent(this@HomeActivity, MapSettingsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var mode : Int = MODE_SETTINGS
        if(item!!.itemId == R.id.action_bluetooth){
            mode = MODE_BLUETOOTH
        }
        startActivity(SettingsActivity().IntentBuilder(this).mode(mode).build())
        return super.onOptionsItemSelected(item)
    }

}
