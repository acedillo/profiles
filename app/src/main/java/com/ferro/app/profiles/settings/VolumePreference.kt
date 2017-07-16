package com.ferro.app.profiles.settings

import android.content.Context
import android.preference.Preference
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import ferro.places.com.profiles.R


/**
 * Created by Abraham on 6/25/2017.
 */
class VolumePreference : Preference {

    private var mVolumeBar : SeekBar? = null
    private var mProgress : Int = 0

    constructor(context: Context) : super(context)
    constructor(context: Context, attributes: AttributeSet) : super(context, attributes)
    constructor(context: Context, attributes: AttributeSet, defStyleAttr : Int) : super(context, attributes, defStyleAttr)

    override fun onCreateView(parent: ViewGroup?): View {
        val view = super.onCreateView(parent)
        mVolumeBar = view.findViewById(R.id.mVolumeBar) as SeekBar

        mVolumeBar!!.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                mProgress = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
               //NOP
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //NOP
            }

        })
        mVolumeBar!!.progress = mProgress
        return view
    }

    fun getProgress() : Int{
        return mVolumeBar!!.progress
    }

    fun setProgress(progress : Int) {
        mVolumeBar!!.progress = progress
        mProgress = progress
    }



}