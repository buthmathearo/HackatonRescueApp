package com.dmi.hackaton.rescue.hospital.ui

import android.content.Intent
import android.os.Bundle
import com.dmi.hackaton.rescue.hospital.R
import com.dmi.hackaton.rescue.hospital.notification.MessagingService
import kotlinx.android.synthetic.main.activity_home.imageView

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()
    
        MessagingService.bus.subscribe { message ->
            val data = Intent(this, NewEmergencyRequestActivity::class.java)
            data.putExtra("KEY_MESSAGE", message)
            startActivity(data)
        }
        
        imageView.setOnClickListener {
            startActivity(Intent(this, NewEmergencyRequestActivity::class.java))
        }
        
    }
}
