package com.ferro.app.profiles.settings

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.preference.*
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
const val DEFAULT_LAT_LONG: Double = 200.0
const val ARG_PREFERENCE: String = "arg.preference"

class SettingsFragment : PreferenceFragment() {

    private val DEFAULT_PREFERENCE_NAME: String = "name.default.setting"

    private var mListener: OnFragmentInteractionListener? = null
    private var mDefaultSettings: PlaceSettings? = null

    private var mVolumePreference: VolumePreference? = null
    private var mWifiPreference: SwitchPreference? = null
    private var mBluetoothPreference: SwitchPreference? = null
    private var mNamePreference: EditTextPreference? = null
    private var mRingtonePreference: RingtonePreference? = null

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

        if (arguments != null && arguments.containsKey(ARG_PREFERENCE)) {
            initializeSettings(arguments.getParcelable(ARG_PREFERENCE))
            return
        }
        object : AsyncTask<Void, Void, PlaceSettings>() {
            override fun doInBackground(vararg params: Void?): PlaceSettings {
                val defaultPlaceSettings = SettingsManager.getPlacesDao(activity).getDefaultPlaceSettings()
                if (defaultPlaceSettings.isEmpty()) {
                    val placeSettings: PlaceSettings = PlaceSettings()
                    placeSettings.name = DEFAULT_PREFERENCE_NAME
                    return placeSettings
                }
                return defaultPlaceSettings[0]
            }

            override fun onPostExecute(result: PlaceSettings?) {
                initializeSettings(result)
            }
        }.execute()
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

    fun save(latitude: Double = DEFAULT_LAT_LONG, longitude: Double = DEFAULT_LAT_LONG, radius: Double = 0.0) {
        fillSettingValues()
        mDefaultSettings!!.longitude = longitude
        mDefaultSettings!!.latitude = latitude
        mDefaultSettings!!.radius = radius
        object : AsyncTask<PlaceSettings, Void, Unit>() {
            override fun doInBackground(vararg params: PlaceSettings?): Unit {
                val defaultPlaceSettings = SettingsManager.getPlacesDao(activity).getDefaultPlaceSettings()
                if (mDefaultSettings!!.latitude == DEFAULT_LAT_LONG) {
                    if (defaultPlaceSettings.isEmpty()) {
                        return SettingsManager.getPlacesDao(activity).addPlace(mDefaultSettings!!)
                    } else {
                        return SettingsManager.getPlacesDao(activity).updatePlace(mDefaultSettings!!)
                    }
                } else {
                    return SettingsManager.getPlacesDao(activity).addPlace(mDefaultSettings!!)
                }
            }

            override fun onPostExecute(result: Unit?) {
                Toast.makeText(activity, "data saved", Toast.LENGTH_LONG).show()
                activity.setResult(Activity.RESULT_OK)
                activity.finish()
            }
        }.execute()
    }

    private fun initializeSettings(settings: PlaceSettings?) {

        if (settings == null) {
            return
        }
        mDefaultSettings = settings
        mVolumePreference = findPreference(getString(R.string.volume_preference_key)) as VolumePreference
        mWifiPreference = findPreference(getString(R.string.wifi_preference_key)) as SwitchPreference
        mBluetoothPreference = findPreference(getString(R.string.bluetooth_preference_key)) as SwitchPreference
        mNamePreference = findPreference(getString(R.string.name_preference_key)) as EditTextPreference
        mRingtonePreference = findPreference(getString(R.string.ringtone_preference_key)) as RingtonePreference

        mWifiPreference!!.isChecked = settings!!.wifiOn
        mBluetoothPreference!!.isChecked = settings.bluetoothOn
        mVolumePreference!!.setProgress(settings.ringtoneVolume)

        mRingtonePreference!!.summary = settings.ringtone
        if (settings.name == DEFAULT_PREFERENCE_NAME) {
            preferenceScreen.removePreference(mNamePreference)
        } else {
            if (settings.name.isBlank()) {
                settings.name = activity.getString(R.string.default_place_setting_name)
            }
            mNamePreference!!.text = settings.name
            mNamePreference!!.summary = settings.name
        }
        setListeners()
    }

    private fun setListeners() {
        if (mNamePreference != null) {
            mNamePreference!!.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { _, newValue ->
                mNamePreference!!.summary = newValue as String
                false
            }
        }
        mRingtonePreference!!.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { _, newValue ->
            mRingtonePreference!!.summary = newValue as String
            false
        }
    }

    private fun fillSettingValues() {
        mDefaultSettings!!.ringtone = mRingtone
        mDefaultSettings!!.bluetoothOn = mBluetoothPreference!!.isChecked
        mDefaultSettings!!.wifiOn = mWifiPreference!!.isChecked
        mDefaultSettings!!.ringtoneVolume = mVolumePreference!!.getProgress()
        mDefaultSettings!!.name = mNamePreference!!.text
    }


    companion object {
        fun newInstance(placeSettings: PlaceSettings): SettingsFragment {
            val fragment = SettingsFragment()
            val args = Bundle()
            args.putParcelable(ARG_PREFERENCE, placeSettings)
            fragment.arguments = args
            return fragment
        }
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
