package com.dmi.hackaton.rescue.user.ui.callback

import com.dmi.hackaton.rescue.user.data.VictimCount

interface VictimCountCallback {
    
    fun onAskForHelp(count: VictimCount)
    
}
