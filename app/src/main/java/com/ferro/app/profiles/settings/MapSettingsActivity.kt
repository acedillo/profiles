package com.ferro.app.profiles.settings

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.SeekBar
import com.ferro.app.profiles.common.activity.MapActivity
import com.ferro.app.profiles.common.data.enitity.PlaceSettings
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import ferro.places.com.profiles.R
import kotlinx.android.synthetic.main.activity_settings.*


class MapSettingsActivity : MapActivity(), SettingsFragment.OnFragmentInteractionListener {

    private val EXTRA_PLACE_SETTING : String = "extra.map.settings.activity.place.settings"
    var mCircle: Circle? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_settings
    }

    override fun getMenuId(): Int {
        return R.menu.menu_settings
    }

    override fun getTitleId(): Int {
        return R.string.title_activity_maps
    }

    override fun setup(savedInstanceState: Bundle?) {
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.mMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
        var placeSettings : PlaceSettings = PlaceSettings()
        if(intent.extras != null && intent.extras!!.containsKey(EXTRA_PLACE_SETTING)){
           placeSettings = intent.extras.getParcelable(EXTRA_PLACE_SETTING)
        }
        val fragment : SettingsFragment = SettingsFragment.newInstance(placeSettings)
        fragmentManager.beginTransaction().add(R.id.mSettingsFragmentContainer, fragment).commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == R.id.action_save){
            val settingsFragment = fragmentManager.findFragmentById(R.id.mSettingsFragmentContainer)
            if(mCircle != null && settingsFragment is SettingsFragment){
                settingsFragment.save(mCircle!!.center.latitude, mCircle!!.center.longitude, mCircle!!.radius)
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        super.onMapReady(googleMap)
        var position : LatLng? = null
        var radius : Double

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

                if(mCircle == null){
                    circleOptions.center(position)
                    mCircle = mMap!!.addCircle(circleOptions)
                }
                mCircle!!.center = position
                radius = progress.toDouble() * 2
                mCircle!!.radius = radius


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

                if(mCircle == null) {
                    circleOptions.center(position)
                    mCircle = mMap!!.addCircle(circleOptions)
                }
                mCircle!!.center = position
            }
        })
    }

    inner class IntentBuilder(context: Context) {
        val intent : Intent = Intent(context, MapSettingsActivity::class.java)

        fun placeSetting(placeSetting : PlaceSettings) : IntentBuilder{
            intent.putExtra(EXTRA_PLACE_SETTING, placeSetting)
            return this
        }

        fun build() : Intent{
            return intent
        }

    }


    override fun sendUserToCurrentLocation(addMarker: Boolean, zoom: Float) {
        super.sendUserToCurrentLocation(true, 17f)
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
