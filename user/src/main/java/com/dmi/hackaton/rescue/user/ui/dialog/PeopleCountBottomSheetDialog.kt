package com.dmi.hackaton.rescue.user.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.View
import com.dmi.hackaton.rescue.user.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.view_people_count.view.btnClose

class PeopleCountBottomSheetDialog : BottomSheetDialogFragment() {
    
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        
        val dialog = super.onCreateDialog(savedInstanceState)
        val view = View.inflate(context, R.layout.view_people_count, null)
        dialog.setContentView(view)
        
        view.btnClose.setOnClickListener {
            dismiss()
        }
        
        BottomSheetBehavior.from(view.parent as View)
        
        return dialog
    }

}
