package abbosbek.mobiler.onlinesavdo.dialog

import abbosbek.mobiler.onlinesavdo.R
import abbosbek.mobiler.onlinesavdo.databinding.ResetPasswordDialogBinding
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

fun Fragment.setupBottomSheetDialog(
    onSendClick : (String) ->Unit
){
    val dialog = BottomSheetDialog(requireContext(),R.style.DialogStyle)
    val binding = ResetPasswordDialogBinding.inflate(layoutInflater)
    dialog.setContentView(binding.root)
    dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
    dialog.show()

    val etEmail = binding.etResetPassword
    val sendBtn = binding.btnSendResetPassword
    val cancelBtn = binding.btnCancelResetPassword

    sendBtn.setOnClickListener {
        val email = etEmail.text.toString().trim()
        onSendClick(email)
        dialog.dismiss()
    }

    cancelBtn.setOnClickListener {
        dialog.dismiss()
    }




}