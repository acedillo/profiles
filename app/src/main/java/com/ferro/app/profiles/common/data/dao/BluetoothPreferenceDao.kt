package com.ferro.app.profiles.common.data.dao

import android.arch.persistence.room.*
import com.ferro.app.profiles.common.data.enitity.BluetoothDevicePreference

/**
 * Created by Abraham on 7/5/2017.
 */
@Dao
interface BluetoothPreferenceDao {

    @Query("SELECT * FROM bluetooth_setting")
    fun getBluetoothDevicePreferenceList() : List<BluetoothDevicePreference>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBluetoothDevicePreferenceList(vararg bluetoothDevicePreferences : BluetoothDevicePreference)

    @Update
    fun updateBluetoothDevicePreference(bluetoothDevicePreference : BluetoothDevicePreference)

    @Delete
    fun deleteBluetoothDevicePreference(bluetoothDevicePreference : BluetoothDevicePreference)
}