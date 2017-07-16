package com.ferro.app.profiles.settings

import android.content.Context
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.RingtonePreference
import android.preference.SwitchPreference
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Toast
import com.ferro.app.profiles.common.data.SettingsManager
import com.ferro.app.profiles.common.data.enitity.PlaceSettings
import ferro.places.com.profiles.R


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SettingsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 */
const val DEFAULT_LAT_LONG : Double = 200.0
class SettingsFragment : PreferenceFragment(){

    private var mListener: OnFragmentInteractionListener? = null
    private var mDefaultSettings: PlaceSettings? = null

    private var mVolumePreference : VolumePreference? = null
    private var mWifiPreference : SwitchPreference? = null
    private var mBluetoothPreference : SwitchPreference? = null

    private var mRingtone: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.prefs)

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        val ringtonePreference = findPreference(getString(R.string.ringtone_preference_key)) as RingtonePreference
        ringtonePreference.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { _, newValue ->
            mRingtone = newValue as String
            true
        }
        object : AsyncTask<Void, Void, PlaceSettings>(){
            override fun doInBackground(vararg params: Void?): PlaceSettings {
                val defaultPlaceSettings = SettingsManager.getPlacesDao(activity).getDefaultPlaceSettings()
                if(defaultPlaceSettings.isEmpty()){
                    return PlaceSettings()
                }
                return defaultPlaceSettings[0]
            }

            override fun onPostExecute(result: PlaceSettings?) {
                initializeSettings(result)
            }
        }.execute()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initializeSettings(settings: PlaceSettings?) {

        if(settings == null){
            return
        }
        mDefaultSettings = settings
        mVolumePreference = findPreference(getString(R.string.volume_preference_key)) as VolumePreference
        mWifiPreference = findPreference(getString(R.string.wifi_preference_key)) as SwitchPreference
        mBluetoothPreference = findPreference(getString(R.string.bluetooth_preference_key)) as SwitchPreference

        mWifiPreference!!.isChecked = settings!!.wifiOn
        mBluetoothPreference!!.isChecked = settings.bluetoothOn
        mVolumePreference!!.setProgress(settings.ringtoneVolume)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    fun save(latitude: Double = DEFAULT_LAT_LONG, longitude: Double = DEFAULT_LAT_LONG, radius : Double = 0.0) {
        fillSettingValues()
        mDefaultSettings!!.longitude = longitude
        mDefaultSettings!!.latitude = latitude
        mDefaultSettings!!.radius = radius
        object : AsyncTask<PlaceSettings, Void, Unit>(){
            override fun doInBackground(vararg params: PlaceSettings?): Unit {
                val defaultPlaceSettings = SettingsManager.getPlacesDao(activity).getDefaultPlaceSettings()
                if(mDefaultSettings!!.latitude == DEFAULT_LAT_LONG) {
                    if (defaultPlaceSettings.isEmpty()) {
                        return SettingsManager.getPlacesDao(activity).addPlace(mDefaultSettings!!)
                    } else {
                        return SettingsManager.getPlacesDao(activity).updatePlace(mDefaultSettings!!)
                    }
                }else{
                    return SettingsManager.getPlacesDao(activity).addPlace(mDefaultSettings!!)
                }
            }

            override fun onPostExecute(result: Unit?) {
                Toast.makeText(activity, "data saved", Toast.LENGTH_LONG).show()
            }
        }.execute()
    }

    private fun fillSettingValues(){
        mDefaultSettings!!.ringtone = mRingtone
        mDefaultSettings!!.bluetoothOn = mBluetoothPreference!!.isChecked
        mDefaultSettings!!.wifiOn = mWifiPreference!!.isChecked
        mDefaultSettings!!.ringtoneVolume = mVolumePreference!!.getProgress()
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }
}
