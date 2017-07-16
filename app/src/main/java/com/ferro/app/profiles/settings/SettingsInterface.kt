package com.ferro.app.profiles.settings

/**
 * Created by Abraham on 7/9/2017.
 */
interface SettingsInterface {
    fun save(latitude : Double = 200.0, longitude: Double = 200.0, radius : Int = 0)
}