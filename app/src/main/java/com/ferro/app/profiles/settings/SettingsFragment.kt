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
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingsFragment : PreferenceFragment(), SettingsInterface {
    private val ARG_PARAM1: String = "settings.fragment.blah"

    private val ARG_PARAM2: String = "settings.fragment.blah"
    private val KEY_RINGTONE_PREFERENCE: String = "ringtone.preference"
    private val REQUEST_CODE_ALERT_RINGTONE: Int = 1
    private var mParam1: String? = null

    private var mParam2: String? = null
    private var mListener: OnFragmentInteractionListener? = null

    private var mRingtone: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
        addPreferencesFromResource(R.xml.prefs)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        var ringtonePreference = findPreference(getString(R.string.ringtone_preference_key)) as RingtonePreference
        ringtonePreference.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { _, newValue ->
            mRingtone = newValue as String
            true
        }
        super.onViewCreated(view, savedInstanceState)
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

    override fun save() {
        val volumePreference = findPreference(getString(R.string.volume_preference_key)) as VolumePreference
        val wifiPreference = findPreference(getString(R.string.wifi_preference_key)) as SwitchPreference
        val bluetoothPreference = findPreference(getString(R.string.bluetooth_preference_key)) as SwitchPreference

        val settings: PlaceSettings = PlaceSettings()

        settings.ringtone = mRingtone
        settings.bluetoothOn = bluetoothPreference.isChecked
        settings.wifiOn = wifiPreference.isChecked
        settings.ringtoneVolume = volumePreference.getProgress()

        object : AsyncTask<PlaceSettings, Void, Unit>(){
            override fun doInBackground(vararg params: PlaceSettings?): Unit {
               return SettingsManager.getPlacesDao(activity).addPlace(settings)
            }

            override fun onPostExecute(result: Unit?) {
                Toast.makeText(activity, "data saved", Toast.LENGTH_LONG).show()
            }
        }.execute()


    }

    private fun setRingtonPreferenceValue(ringtone: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @param param1 Parameter 1.
     * *
     * @param param2 Parameter 2.
     * *
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    fun newInstance(param1: String, param2: String): SettingsFragment {
        val fragment = SettingsFragment()
        val args = Bundle()
        args.putString(ARG_PARAM1, param1)
        args.putString(ARG_PARAM2, param2)
        fragment.arguments = args
        return fragment
    }
}
