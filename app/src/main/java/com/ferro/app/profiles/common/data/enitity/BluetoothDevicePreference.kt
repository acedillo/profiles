package com.ferro.app.profiles.common.data.enitity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Abraham on 7/4/2017.
 */
@Entity(tableName = "setting")
open class BluetoothDevicePreference {

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
    @ColumnInfo(name = "name")
    val name: String = ""
    @ColumnInfo(name = "volume_level")
    val volumeLevel: Int = 0
}