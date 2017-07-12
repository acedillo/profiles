package com.ferro.app.profiles.settings

import android.content.Context
import android.preference.Preference
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import ferro.places.com.profiles.R




/**
 * Created by Abraham on 6/25/2017.
 */
class VolumePreference : Preference {

    private var mVolumeBar : SeekBar? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attributes: AttributeSet) : super(context, attributes)
    constructor(context: Context, attributes: AttributeSet, defStyleAttr : Int) : super(context, attributes, defStyleAttr)

    override fun onCreateView(parent: ViewGroup?): View {
        val li = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return li.inflate(R.layout.preference_volume, parent, false)
    }

    override fun onBindView(view: View?) {
        super.onBindView(view)
        //TODO find a better way to get the seekBar
        mVolumeBar = (view as ViewGroup).getChildAt(1) as SeekBar
    }




    fun getProgress() : Int{
        return mVolumeBar!!.progress
    }

}