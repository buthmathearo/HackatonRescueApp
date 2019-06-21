package com.dmi.hackaton.rescue.hospital.ui

import android.app.Activity
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import com.dmi.hackaton.rescue.hospital.R
import com.dmi.hackaton.rescue.hospital.data.MyMessage
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_show_more_direction.btnNoNeed
import kotlinx.android.synthetic.main.activity_show_more_direction.btnShowDirection

class ShowMeDirectionActivity : BaseActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_show_more_direction)
        setResult(Activity.RESULT_OK)
        
        btnNoNeed.setOnClickListener {
            finish()
        }
        
        btnShowDirection.setOnClickListener {
            val json = intent.getStringExtra("MY_MESSAGE")
            val message = Gson().fromJson(json, MyMessage::class.java)
            
            val uri = Uri.parse("google.navigation:q=${message.latitude},${message.longitude}")
            val mapIntent = Intent(ACTION_VIEW, uri)
            mapIntent.setPackage("com.google.android.apps.maps")
            if (mapIntent.resolveActivity(packageManager) != null) {
                startActivity(mapIntent)
            }
        }
    }
}
