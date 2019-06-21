package com.dmi.hackaton.rescue.hospital.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.dmi.hackaton.rescue.core.api.ApiProvider
import com.dmi.hackaton.rescue.hospital.R
import com.dmi.hackaton.rescue.hospital.data.EmergencyAcceptedRequest
import com.dmi.hackaton.rescue.hospital.data.MyMessage
import com.dmi.hackaton.rescue.hospital.repository.HospitalRemoteService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SendingResponseToRequesterActivity : BaseActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.view_ambulance_loading)
        
        val remote = ApiProvider.getRetrofit().create(HospitalRemoteService::class.java)
        
        val file = getSharedPreferences(LoginActivity.LOGIN_INFO_SHARED_PREF, Context.MODE_PRIVATE)
        val myIntent = Intent(intent)
        val remoteMessage = myIntent.getParcelableExtra<RemoteMessage>("KEY_MESSAGE")
        val message = Gson().fromJson(remoteMessage.data["data"], MyMessage::class.java)
        val alertId = message.alertId
        val userId = file.getString(LoginActivity.KEY_USER_ID, null)
        
        if (userId != null) {
            remote.acceptEmergency(EmergencyAcceptedRequest(alertId, userId!!.toLong()))
                    .delay(3, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.d("@@@", "accepted success")
                        //setResult(Activity.RESULT_OK)
                        //finish()
                        val zzIntent = Intent(this, ShowMeDirectionActivity::class.java)
                        zzIntent.putExtra("MY_MESSAGE", remoteMessage.data["data"])
                        startActivityForResult(zzIntent, 2)
                    }, {
                        Log.d("@@@", "accepted failed")
                    })
        }
    }
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 2) {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}
