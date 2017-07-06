package com.ferro.app.profiles.common.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.ferro.app.profiles.common.data.dao.BluetoothPreferenceDao
import com.ferro.app.profiles.common.data.dao.PlacesDao
import com.ferro.app.profiles.common.data.enitity.BluetoothDevicePreference
import com.ferro.app.profiles.common.data.enitity.PlaceSettings

/**
 * Created by Abraham on 7/5/2017.
 */

@Database(entities = arrayOf(PlaceSettings::class, BluetoothDevicePreference::class), version = 1)
abstract class PlacesDataBase : RoomDatabase() {
    abstract fun placesDao(): PlacesDao
    abstract fun bluetoothDevicesDao() : BluetoothPreferenceDao
}