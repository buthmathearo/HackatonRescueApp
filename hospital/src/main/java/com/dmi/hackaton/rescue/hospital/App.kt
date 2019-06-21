package com.dmi.hackaton.rescue.hospital

import android.content.Context
import com.dmi.hackaton.rescue.core.BaseApp
import com.dmi.hackaton.rescue.hospital.ui.LoginActivity

class App : BaseApp() {
    
    override fun getUserId(): Long? {
        val file = getSharedPreferences(LoginActivity.LOGIN_INFO_SHARED_PREF, Context.MODE_PRIVATE)
        return file.getString(LoginActivity.KEY_USER_ID, null)?.toLong()
    }
    
    override fun shouldSendWhenNoUserId(): Boolean {
        return getUserId() != null
    }
}
