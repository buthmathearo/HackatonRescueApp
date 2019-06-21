package com.dmi.hackaton.rescue.user.intent

import android.content.Context
import android.content.Intent
import com.dmi.hackaton.rescue.user.data.VictimCount
import com.dmi.hackaton.rescue.user.ui.SearchingNearbyActivity

class SearchingNearbyIntent : Intent {
    
    constructor(intent: Intent) : super(intent)
    
    constructor(context: Context, victimCount: VictimCount) : super(context, SearchingNearbyActivity::class.java) {
        putExtra(VICTIM_COUNT, victimCount.value)
    }
    
    fun getVictimCount(): Int {
        return getIntExtra(VICTIM_COUNT, VictimCount.UNKNOWN.value)
    }
    
    companion object {
        private const val VICTIM_COUNT = "VICTIM_COUNT"
    }
}
