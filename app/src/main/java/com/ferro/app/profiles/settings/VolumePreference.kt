package com.ferro.app.profiles.settings

import android.content.Context
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceViewHolder
import android.util.AttributeSet
import ferro.places.com.profiles.R


/**
 * Created by Abraham on 6/25/2017.
 */
class VolumePreference : Preference{

    constructor(context: Context) : super(context)
    constructor(context: Context, attributes: AttributeSet) : super(context, attributes){
        layoutResource = R.layout.preference_volume
    }
    constructor(context: Context, attributes: AttributeSet, defStyleAttr : Int) : super(context, attributes, defStyleAttr){
        layoutResource = R.layout.preference_volume
    }

    override fun onBindViewHolder(holder: PreferenceViewHolder?) {

        super.onBindViewHolder(holder)
    }



}