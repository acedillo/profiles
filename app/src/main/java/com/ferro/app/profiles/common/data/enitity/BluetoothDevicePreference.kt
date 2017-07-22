package com.ferro.app.profiles.common.data.enitity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable


/**
 * Created by Abraham on 7/4/2017.
 */
@Entity(tableName = "Bluetooth_setting")
open class BluetoothDevicePreference(): Parcelable {

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

companion object { @JvmField val CREATOR: Parcelable.Creator<BluetoothDevicePreference> = object : Parcelable.Creator<BluetoothDevicePreference> {override fun createFromParcel(source: Parcel): BluetoothDevicePreference = BluetoothDevicePreference(source)
override fun newArray(size: Int): Array<BluetoothDevicePreference?> =arrayOfNulls(size)}}

constructor(source: Parcel) : this(
)

override fun describeContents() = 0

override fun writeToParcel(dest: Parcel, flags: Int) {}
}