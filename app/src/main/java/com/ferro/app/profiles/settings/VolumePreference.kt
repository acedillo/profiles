package com.ferro.app.profiles.settings

import android.content.Context
import android.preference.Preference
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar




/**
 * Created by Abraham on 6/25/2017.
 */
class VolumePreference : Preference {

    private var mVolumeBar : SeekBar? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attributes: AttributeSet) : super(context, attributes)
    constructor(context: Context, attributes: AttributeSet, defStyleAttr : Int) : super(context, attributes, defStyleAttr)

    override fun onBindView(view: View?) {
        super.onBindView(view)
        //TODO find a better way to get the seekBar
        mVolumeBar = (view as ViewGroup).getChildAt(1) as SeekBar
    }

    fun getProgress() : Int{
        return mVolumeBar!!.progress
    }

    fun setProgress(progress : Int) {
        mVolumeBar!!.progress = progress
    }

}