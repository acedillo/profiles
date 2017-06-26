package com.ferro.app.profiles.common

import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity

/**
 * Activity with a toolbar and container for the rest of the app
 * Created by Abraham on 6/25/2017.
 */
abstract class BaseActivity : AppCompatActivity(){

    abstract @LayoutRes fun getLayoutId() : Int
}