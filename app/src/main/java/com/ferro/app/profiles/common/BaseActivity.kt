package com.ferro.app.profiles.common

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import ferro.places.com.profiles.R
import kotlinx.android.synthetic.main.activity_base.*

/**
 * Activity with a toolbar and container for the rest of the app
 * Created by Abraham on 6/25/2017.
 */
abstract class BaseActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        activityFLContainer.addView( layoutInflater.inflate(getLayoutId(), null))
        setSupportActionBar(mToolbar)
        setup()
    }
    abstract @LayoutRes fun getLayoutId() : Int
    abstract fun setup()
}