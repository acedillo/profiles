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
    var id: Int = 0
    @ColumnInfo(name = "name")
    var name: String = ""
    @ColumnInfo(name = "latitude")
    var latitude: Double = 200.0
    @ColumnInfo(name = "longitude")
    var longitude: Double = 200.0
    @ColumnInfo(name = "radius")
    var radius : Double = 0.0
    @ColumnInfo(name = "ringtone")
    var ringtone: String = ""
    @ColumnInfo(name = "volume")
    var ringtoneVolume: Int = 0
    @ColumnInfo(name = "wifi_on")
    var wifiOn: Boolean = true
    @ColumnInfo (name = "bluetooth_on")
    var bluetoothOn: Boolean = true
}