package com.dmi.hackaton.rescue.user.ui

import android.os.Bundle
import com.dmi.hackaton.rescue.user.R

class SearchingNearbyActivity : BaseActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_searching_nearby)
    }

}
