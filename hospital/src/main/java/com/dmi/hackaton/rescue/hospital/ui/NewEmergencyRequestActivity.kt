package com.dmi.hackaton.rescue.hospital.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.dmi.hackaton.rescue.hospital.R
import com.dmi.hackaton.rescue.hospital.data.MyMessage
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_new_emergency_request.btnSendAmbulance
import kotlinx.android.synthetic.main.activity_new_emergency_request.phoneNumber

class NewEmergencyRequestActivity : BaseActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_new_emergency_request)
        
        val myIntent = Intent(intent)
        val remoteMessage = myIntent.getParcelableExtra<RemoteMessage>("KEY_MESSAGE")
        
        val message = Gson().fromJson(remoteMessage.data["data"], MyMessage::class.java)
        
        val numOfVictim = message.numberOfVictim
        phoneNumber.text = numOfVictim.toString()
        
        btnSendAmbulance.setOnClickListener {
            openLoading(myIntent.getParcelableExtra("KEY_MESSAGE"))
        }
        
    }
    
    private fun openLoading(data: RemoteMessage) {
        val newIntent = Intent(this, SendingResponseToRequesterActivity::class.java)
        newIntent.putExtra("KEY_MESSAGE", data)
        startActivityForResult(newIntent, 1)
    }
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            finish()
        }
    }
}
