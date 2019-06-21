package com.dmi.hackaton.rescue.user.ui

import android.os.Bundle
import com.dmi.hackaton.rescue.user.R
import com.dmi.hackaton.rescue.user.intent.SearchingNearbyIntent
import com.dmi.hackaton.rescue.user.ui.dialog.PeopleCountBottomSheetDialog
import kotlinx.android.synthetic.main.view_home.button

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        
        // Hide Toolbar.
        supportActionBar?.hide()
        val dialog = PeopleCountBottomSheetDialog()
        button.setOnClickListener {
            /*if (!dialog.isAdded) {
                dialog.show(supportFragmentManager, "")
            }*/
            startActivity(SearchingNearbyIntent(this))
        }
    }
}
