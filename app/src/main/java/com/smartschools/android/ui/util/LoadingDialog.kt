package com.smartschools.android.ui.util

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle


class LoadingDialog(context: Context) : Dialog(context) {

    init {
        this.window?.setBackgroundDrawable(ColorDrawable(0))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.dialog_loading)
        this.setCancelable(false)
        this.setCanceledOnTouchOutside(false)
    }
}