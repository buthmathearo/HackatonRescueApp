package com.dmi.hackaton.rescue.hospital.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle

class RootActivity : BaseActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val sharedPref = getSharedPreferences(LoginActivity.LOGIN_INFO_SHARED_PREF, Context.MODE_PRIVATE)
        if (sharedPref.getString(LoginActivity.KEY_USER_ID, null) == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }
    
}
