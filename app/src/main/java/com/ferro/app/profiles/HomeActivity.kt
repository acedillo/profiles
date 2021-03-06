package com.ferro.app.profiles

import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.MenuItem
import com.ferro.app.profiles.common.activity.MapActivity
import com.ferro.app.profiles.common.data.SettingsManager
import com.ferro.app.profiles.common.data.enitity.PlaceSettings
import com.ferro.app.profiles.settings.MODE_BLUETOOTH
import com.ferro.app.profiles.settings.MODE_SETTINGS
import com.ferro.app.profiles.settings.MapSettingsActivity
import com.ferro.app.profiles.settings.SettingsActivity
import com.ferro.app.profiles.util.MapUtil
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker
import ferro.places.com.profiles.R
import kotlinx.android.synthetic.main.activity_home.*

const val ACTIVITY_REQUEST_DATA_SAVED: Int = 1001
class HomeActivity : MapActivity(), GoogleMap.OnInfoWindowClickListener {

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
            val placeSetting : PlaceSettings = PlaceSettings()
            placeSetting.longitude = mMap!!.cameraPosition!!.target!!.longitude
            placeSetting.latitude = mMap!!.cameraPosition!!.target!!.latitude
            val intent = MapSettingsActivity().IntentBuilder(this@HomeActivity).placeSetting(placeSetting).build()
            startActivityForResult(intent, ACTIVITY_REQUEST_DATA_SAVED)
        }
        loadMarkers()

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var mode : Int = MODE_SETTINGS
        if(item!!.itemId == R.id.action_bluetooth){
            mode = MODE_BLUETOOTH
        }
        startActivity(SettingsActivity().IntentBuilder(this).mode(mode).build())
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == ACTIVITY_REQUEST_DATA_SAVED){
            if(resultCode == Activity.RESULT_OK){
                mMap!!.clear()
                loadMarkers()
            }
        }
    }

    override fun onInfoWindowClick(marker: Marker?){
        if(marker != null && marker.tag is PlaceSettings){
            val place : PlaceSettings = marker.tag as PlaceSettings
            val intent = MapSettingsActivity().IntentBuilder(this).placeSetting(place).build()
            startActivityForResult(intent, ACTIVITY_REQUEST_DATA_SAVED)
        }
    }

    private fun loadMarkers(){
        object : AsyncTask<Unit, Unit, List<PlaceSettings>>(){
            override fun doInBackground(vararg params: Unit?): List<PlaceSettings> {
                return SettingsManager.getPlacesDao(this@HomeActivity).getPlaceList()
            }

            override fun onPostExecute(result: List<PlaceSettings>?) {
                MapUtil.loadMarkersIntoMap(result!!, mMap!!)
                mMap!!.setOnInfoWindowClickListener(this@HomeActivity)
                super.onPostExecute(result)
            }
        }.execute()
    }



}
