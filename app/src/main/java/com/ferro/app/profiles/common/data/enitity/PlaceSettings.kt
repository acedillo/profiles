package com.ferro.app.profiles.common.data.enitity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Abraham on 7/4/2017.
 */
@Entity(tableName = "setting")
class PlaceSettings (): Parcelable {
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

companion object { @JvmField val CREATOR: Parcelable.Creator<PlaceSettings> = object : Parcelable.Creator<PlaceSettings> {override fun createFromParcel(source: Parcel): PlaceSettings = PlaceSettings(source)
override fun newArray(size: Int): Array<PlaceSettings?> =arrayOfNulls(size)}}

constructor(source: Parcel) : this()

override fun describeContents() = 0

override fun writeToParcel(dest: Parcel, flags: Int) {}
}