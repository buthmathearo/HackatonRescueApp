package com.dmi.hackaton.rescue.user.intent

import android.content.Context
import android.content.Intent
import com.dmi.hackaton.rescue.user.ui.SearchingNearbyActivity

class SearchingNearbyIntent : Intent {
    
    constructor(intent: Intent) : super(intent)
    
    constructor(context: Context) : super(context, SearchingNearbyActivity::class.java)
}
