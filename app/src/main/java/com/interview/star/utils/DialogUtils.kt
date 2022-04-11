package com.interview.star.utils

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

object DialogUtils {
    fun showMessage(
        context: Context?, title: String? = null, message: String? = null,
        textPositive: String? = null, positiveListener: DialogInterface.OnClickListener? = null,
        cancelable: Boolean = false, canceledOnTouchOutside: Boolean = false
    ): AlertDialog? {
        if (context == null) return null
        return AlertDialog.Builder(context).apply {
            setTitle(title)
            setMessage(message)
            if (textPositive != null) {
                setPositiveButton(textPositive, positiveListener)
            }
            setCancelable(cancelable)

        }.create().apply {
            setCanceledOnTouchOutside(canceledOnTouchOutside)
            show()
        }
    }
}