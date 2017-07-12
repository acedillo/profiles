package com.ferro.app.profiles.common.data

import android.arch.persistence.room.Room
import android.content.Context
import com.ferro.app.profiles.common.data.dao.BluetoothPreferenceDao
import com.ferro.app.profiles.common.data.dao.PlacesDao
import com.ferro.app.profiles.common.data.database.PlacesDataBase

/**
 * Created by Abraham on 7/5/2017.
 */
object SettingsManager {
    var mDataBase: PlacesDataBase? = null

    private fun getDataBase(context: Context) : PlacesDataBase?{
        if(mDataBase == null){
            mDataBase = Room.databaseBuilder(context, PlacesDataBase::class.java, "places-database").build()
        }
        return mDataBase
    }

    fun getPlacesDao(context: Context) : PlacesDao{
       return getDataBase(context)!!.placesDao()
    }

    fun getBluetoothDevicesDao(context: Context) : BluetoothPreferenceDao{
        return getDataBase(context)!!.bluetoothDevicesDao()
    }

}