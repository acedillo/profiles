package com.ferro.app.profiles.common.data.enitity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Abraham on 7/4/2017.
 */
@Entity(tableName = "setting")
class PlaceSettings {
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
    @ColumnInfo(name = "name")
    val name: String = ""
    @ColumnInfo(name = "latitude")
    val latitude: Double = 0.0
    @ColumnInfo(name = "longitude")
    val longitude: Double = 0.0
    @ColumnInfo(name = "radius")
    val radius : Int = 0
    @ColumnInfo(name = "ringtone")
    val ringtone: String = ""
    @ColumnInfo(name = "volume")
    val ringtoneVolume: Int = 0
    @ColumnInfo(name = "wifi_on")
    val wifiOn: Boolean = false
    @ColumnInfo (name = "bluetooth_on")
    val bluetoothOn: Boolean = false
}