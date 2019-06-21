package com.dmi.hackaton.rescue.hospital.ui

import android.content.Intent
import android.os.Bundle
import com.dmi.hackaton.rescue.hospital.App
import com.dmi.hackaton.rescue.hospital.R
import kotlinx.android.synthetic.main.activity_login.btnLogin
import kotlinx.android.synthetic.main.activity_login.userId

class LoginActivity : BaseActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        
        setContentView(R.layout.activity_login)
        
        val sharedPref = getSharedPreferences(LOGIN_INFO_SHARED_PREF, MODE_PRIVATE)
        
        btnLogin.setOnClickListener {
            val id = userId.text.toString()
            if (id.isNotBlank()) {
                sharedPref.edit().putString(KEY_USER_ID, userId.text.toString()).apply()
                
                (application as App).registerNotification {
                    openHome()
                }
            }
        }
    }
    
    private fun openHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
    
    companion object {
        const val LOGIN_INFO_SHARED_PREF = "LOGIN_INFO_SHARED_PREF"
        const val KEY_USER_ID = "KEY_USER_ID"
    }
}
