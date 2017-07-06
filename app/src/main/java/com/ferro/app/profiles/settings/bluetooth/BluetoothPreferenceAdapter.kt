package com.ferro.app.profiles.settings.bluetooth

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import com.ferro.app.profiles.common.data.enitity.BluetoothDevicePreference
import ferro.places.com.profiles.R


/**
 * Created by Abraham on 7/3/2017.
 */
class BluetoothPreferenceAdapter(devicesList: List<BluetoothDevicePreference>) : RecyclerView.Adapter<BluetoothPreferenceAdapter.ViewHolder>() {

    private val mDevicesList : List<BluetoothDevicePreference> = devicesList

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.mTextView.text = mDevicesList[position].name
        holder.mSeekBar.progress = mDevicesList[position].volumeLevel
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent!!.context).
                inflate(R.layout.view_bluetooth_preference, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mDevicesList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val mTextView : TextView = view.findViewById(R.id.mDeviceName) as TextView
        val mSeekBar : SeekBar = view.findViewById(R.id.mVolumeSeekBar) as SeekBar
    }
}