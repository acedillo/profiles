package com.ferro.app.profiles.common.data.enitity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Abraham on 7/4/2017.
 */
@Entity(tableName = "Bluetooth_setting")
open class BluetoothDevicePreference(){

    constructor(name : String, volumeLevel : Int) : this(){
        this.volumeLevel = volumeLevel
        this.name = name
    }

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    @ColumnInfo(name = "name")
    var name: String = ""
    @ColumnInfo(name = "volume_level")
    var volumeLevel: Int = 0
}