package com.example.text_ceiba.presentation.common.loading_alert;

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.example.text_ceiba.R

class ShowDialogs {
    companion object{
        fun showLoadingDialog(context: Context): AlertDialog {
            val builder:AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setTitle(R.string.loading_title)
                .setView(R.layout.dialog_loading)
                .setCancelable(false)
            val alertDialogLoading = builder.create()
            alertDialogLoading.show()
            return alertDialogLoading
        }
    }
}
