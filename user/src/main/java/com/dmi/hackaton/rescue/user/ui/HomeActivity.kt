package com.dmi.hackaton.rescue.user.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.dmi.hackaton.rescue.user.data.VictimCount
import com.dmi.hackaton.rescue.user.intent.SearchingNearbyIntent
import com.dmi.hackaton.rescue.user.ui.callback.VictimCountCallback
import com.dmi.hackaton.rescue.user.ui.dialog.SuccessDialogFragment
import com.dmi.hackaton.rescue.user.ui.dialog.VictimCountBottomSheetDialog
import kotlinx.android.synthetic.main.view_home.button

class HomeActivity : BaseActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(com.dmi.hackaton.rescue.user.R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(com.dmi.hackaton.rescue.user.R.layout.activity_home)
        
        // Hide Toolbar.
        supportActionBar?.hide()
        val dialog = VictimCountBottomSheetDialog()
        
        dialog.victimCountCallback = object : VictimCountCallback {
            override fun onAskForHelp(count: VictimCount) {
                openSearchingNearby(count)
            }
        }
        
        button.setOnClickListener {
            if (!dialog.isAdded) {
                dialog.show(supportFragmentManager, "")
            }
        }
    }
    
    private fun openSearchingNearby(victimCount: VictimCount) {
        startActivityForResult(SearchingNearbyIntent(this, victimCount), RC_CODE)
    }
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == RC_CODE) {
            SuccessDialogFragment().show(supportFragmentManager, SuccessDialogFragment.TAG)
        }
    }
    
    companion object {
        private const val RC_CODE = 1
    }
}
