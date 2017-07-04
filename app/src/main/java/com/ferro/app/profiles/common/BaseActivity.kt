package com.ferro.app.profiles.common

import android.app.Fragment
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.MenuRes
import android.support.annotation.StringRes
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import ferro.places.com.profiles.R
import kotlinx.android.synthetic.main.activity_base.*

/**
 * Activity with a toolbar and container for the rest of the app to use
 * Created by Abraham on 6/25/2017.
 */
abstract class BaseActivity : AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        if(getLayoutId() != 0) {
            activityFLContainer.addView(layoutInflater.inflate(getLayoutId(), null))
        }
        setSupportActionBar(mToolbar)
        supportActionBar?.setTitle(getTitleId())
        setup(savedInstanceState)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(getMenuId(), menu)
        return super.onPrepareOptionsMenu(menu)
    }

    /**
     * Method to add a fragment to the main layout
     */
    open fun addFragment(fragment: Fragment){
        fragmentManager.beginTransaction()
                .add(R.id.activityFLContainer, fragment)
                .commit()
    }

    /**
     * Method to attach the layout into the base activity layout, if no layout is necessary
     * do not implement
     */
     @LayoutRes
     open fun getLayoutId() : Int{
         return 0
     }
    abstract @MenuRes fun getMenuId() : Int
    abstract @StringRes fun getTitleId() : Int
    abstract fun setup(savedInstanceState: Bundle?)
}