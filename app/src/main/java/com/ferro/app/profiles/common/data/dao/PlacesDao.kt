package com.ferro.app.profiles.common.data.dao

import android.arch.persistence.room.*
import com.ferro.app.profiles.common.data.enitity.PlaceSettings

/**
 * Created by Abraham on 7/4/2017.
 */
@Dao
interface PlacesDao {
    @Query("SELECT * FROM setting")
    fun getPlaceList() : List<PlaceSettings>

    @Query("SELECT * FROM setting WHERE latitude == 200 AND longitude == 200")
    fun getDefaultPlaceSettings() : List<PlaceSettings>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPlace(vararg places : PlaceSettings)

    @Update
    fun updatePlace(place : PlaceSettings)

    @Delete
    fun deletePlace(place : PlaceSettings)

}