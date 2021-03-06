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
            val fragment = fragmentManager.findFragmentByTag(SettingsFragment::class.java.name)
            if(fragment == null) {
                addFragment(SettingsFragment())
            }
        } else {
            val fragment = fragmentManager.findFragmentByTag(BluetoothSettingsFragment::class.java.name)
            if(fragment == null) {
                addFragment(BluetoothSettingsFragment())
            }
        }
    }

    override fun onBackPressed() {
        if(fragmentManager.backStackEntryCount <= 1){
            finish()
            return
        }
        super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val fragment = getCurrentFragment()
        if(item!!.itemId == R.id.action_save){
            if(fragment is SettingsFragment){
                fragment.save()
            }
            if(fragment is BluetoothSettingsFragment){
                fragment.save()
            }
        }else if(item.itemId == R.id.action_delete){
            if(fragment is SettingsFragment){
                fragment.delete()
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
