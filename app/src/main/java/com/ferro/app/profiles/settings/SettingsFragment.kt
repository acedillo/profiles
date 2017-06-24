package com.ferro.app.profiles.settings

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.preference.PreferenceFragmentCompat
import ferro.places.com.profiles.R
import android.media.RingtoneManager
import android.content.Intent
import android.provider.Settings
import android.provider.Settings.System.DEFAULT_NOTIFICATION_URI
import android.support.v7.preference.Preference
import android.media.Ringtone




/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SettingsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingsFragment : PreferenceFragmentCompat() {

    private val ARG_PARAM1 : String = "settings.fragment.blah"
    private val ARG_PARAM2 : String = "settings.fragment.blah"
    private val KEY_RINGTONE_PREFERENCE: String = "ring_tone_preference"
    private val REQUEST_CODE_ALERT_RINGTONE: Int = 1

    private var mParam1: String? = null
    private var mParam2: String? = null

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context as OnFragmentInteractionListener?
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.prefs, rootKey)
    }



    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        if (preference.getKey().equals(KEY_RINGTONE_PREFERENCE)) {
            val intent = Intent(RingtoneManager.ACTION_RINGTONE_PICKER)
            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_RINGTONE)
            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true)
            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, true)
            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_DEFAULT_URI, Settings.System.DEFAULT_RINGTONE_URI)

            val existingValue = getRingtonePreferenceValue()
            if (existingValue != null) {
                if (existingValue!!.length == 0) {
                    // Select "Silent"
                    intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, null as Uri?)
                } else {
                    intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, Uri.parse(existingValue))
                }
            } else {
                // No ringtone has been selected, set to the default
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, Settings.System.DEFAULT_NOTIFICATION_URI)
            }

            startActivityForResult(intent, REQUEST_CODE_ALERT_RINGTONE)
            return true
        } else {
            return super.onPreferenceTreeClick(preference)
        }
    }

    private fun getRingtonePreferenceValue(): String {
        return RingtoneManager.getActualDefaultRingtoneUri(activity.applicationContext, RingtoneManager.TYPE_RINGTONE).path
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_ALERT_RINGTONE && data != null) {
            val ringtone = data.getParcelableExtra<Uri>(RingtoneManager.EXTRA_RINGTONE_PICKED_URI)
            if (ringtone != null) {
                setRingtonPreferenceValue(ringtone.toString()) // TODO
            } else {
                // "Silent" was selected
                setRingtonPreferenceValue("") // TODO
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
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
