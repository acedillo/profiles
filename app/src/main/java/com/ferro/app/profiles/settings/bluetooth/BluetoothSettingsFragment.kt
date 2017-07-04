package com.ferro.app.profiles.settings.bluetooth

import android.Manifest
import android.app.Fragment
import android.bluetooth.BluetoothAdapter
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ferro.app.profiles.settings.bluetooth.model.BluetoothDevicePreference
import ferro.places.com.profiles.R
import kotlinx.android.synthetic.main.fragment_bluetooth_settings.*

class BluetoothSettingsFragment : Fragment(), ActivityCompat.OnRequestPermissionsResultCallback  {

    private val PERMISSIONS_REQUEST_BLUETOOTH = 0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.fragment_bluetooth_settings, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        setup()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setup() {
        val permission : Int = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
        if (permission == PackageManager.PERMISSION_GRANTED) {
            initBluetoothDevices()
        }else {
            ActivityCompat.requestPermissions(activity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    PERMISSIONS_REQUEST_BLUETOOTH)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSIONS_REQUEST_BLUETOOTH -> if (grantResults.isEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initBluetoothDevices()
            }
        }
    }

    private fun initBluetoothDevices(){
        val bluetoothAdapter = BluetoothPreferenceAdapter(getBluetoothDevices())

        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.adapter = bluetoothAdapter

    }

    private fun getBluetoothDevices(): List<BluetoothDevicePreference> {

        val bluetoothDeviceList : MutableList<BluetoothDevicePreference> = arrayListOf()
        val bondedDevices = BluetoothAdapter.getDefaultAdapter().bondedDevices

        bondedDevices.mapTo(bluetoothDeviceList) {
            BluetoothDevicePreference(it.name, 0)
        }
        return bluetoothDeviceList
    }


}
