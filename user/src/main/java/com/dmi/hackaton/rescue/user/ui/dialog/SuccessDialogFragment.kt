package com.dmi.hackaton.rescue.user.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.dmi.hackaton.rescue.user.R
import kotlinx.android.synthetic.main.dialog_success.view.btnGotIt

class SuccessDialogFragment : BaseDialogFragment() {
    
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = AlertDialog.Builder(context!!)
        
        val view = View.inflate(context, R.layout.dialog_success, null)
        
        view.btnGotIt.setOnClickListener {
            dismiss()
        }
        
        dialog.setView(view)
        dialog.setCancelable(false)
        return dialog.create()
    }
    
    companion object {
        const val TAG = "SuccessDialogFragment"
    }
}
