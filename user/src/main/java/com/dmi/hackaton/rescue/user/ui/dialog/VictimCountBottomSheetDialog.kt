package com.dmi.hackaton.rescue.user.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.View
import com.dmi.hackaton.rescue.user.R
import com.dmi.hackaton.rescue.user.data.VictimCount
import com.dmi.hackaton.rescue.user.ui.callback.VictimCountCallback
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.view_people_count.view.btnClose
import kotlinx.android.synthetic.main.view_people_count.view.btnFourPlus
import kotlinx.android.synthetic.main.view_people_count.view.btnOneTwo
import kotlinx.android.synthetic.main.view_people_count.view.btnThreeFour
import kotlinx.android.synthetic.main.view_people_count.view.btnUnknown

class VictimCountBottomSheetDialog : BottomSheetDialogFragment() {
    
    var victimCountCallback: VictimCountCallback? = null
    
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        
        val dialog = super.onCreateDialog(savedInstanceState)
        val view = View.inflate(context, R.layout.view_people_count, null)
        dialog.setContentView(view)
        
        view.btnClose.setOnClickListener {
            dismiss()
        }
        
        BottomSheetBehavior.from(view.parent as View)
        
        setupEvents(view)
        
        return dialog
    }
    
    private fun setupEvents(view: View) {
        view.btnOneTwo.setOnClickListener {
            dismiss()
            victimCountCallback?.onAskForHelp(VictimCount.ONE_TWO)
        }
        
        view.btnThreeFour.setOnClickListener {
            dismiss()
            victimCountCallback?.onAskForHelp(VictimCount.THREE_FOUR)
        }
        
        view.btnFourPlus.setOnClickListener {
            dismiss()
            victimCountCallback?.onAskForHelp(VictimCount.FOUR_PLUS)
        }
        
        view.btnUnknown.setOnClickListener {
            dismiss()
            victimCountCallback?.onAskForHelp(VictimCount.UNKNOWN)
        }
    }
    
}
