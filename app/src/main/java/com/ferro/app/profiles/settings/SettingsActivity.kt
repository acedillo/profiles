package com.ferro.app.profiles.settings

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import com.ferro.app.profiles.common.activity.BaseActivity
import com.ferro.app.profiles.settings.bluetooth.BluetoothSettingsFragment
import ferro.places.com.profiles.R

const val MODE_SETTINGS: Int = 0
const val MODE_BLUETOOTH: Int = 1

class SettingsActivity : BaseActivity(), SettingsFragment.OnFragmentInteractionListener {

    val EXTRA_MODE: String = "extras.mode"

    override fun onFragmentInteraction(uri: Uri) {
        //NOP
    }

    override fun getTitleId(): Int {
        return R.string.title_default_settings
    }

    override fun getMenuId(): Int {
        return R.menu.menu_settings
    }

    override fun setup(savedInstanceState: Bundle?) {
        var mode: Int = MODE_SETTINGS
        val extras: Bundle = intent.extras
        if (extras.containsKey(EXTRA_MODE)) {
            mode = extras.getInt(EXTRA_MODE)
        }
        if (mode == MODE_SETTINGS) {
            addFragment(SettingsFragment())
        } else {
            addFragment(BluetoothSettingsFragment())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == R.id.action_save){
            val fragment = getCurrentFragment()
            if(fragment is SettingsFragment){
                fragment.save()
            }
            if(fragment is BluetoothSettingsFragment){
                fragment.save()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    inner class IntentBuilder(context: Context) {

        private val mIntent: Intent = Intent(context, SettingsActivity::class.java)

        fun mode(mode: Int): IntentBuilder {
            mIntent.putExtra(EXTRA_MODE, mode)
            return this
        }

        fun build(): Intent {
            return mIntent
        }
    }

}
