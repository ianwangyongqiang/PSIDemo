@file:Suppress("DEPRECATION")

package com.skycopyhot.psidemo.ui

import android.app.Dialog
import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import com.skycopyhot.psidemo.R

/**
 * Created by yongqiangwang on 17/2/18.
 * Progress
 */
class Progress : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val pd = ProgressDialog(activity)
        pd.setMessage(getString(R.string.please_wait))
        pd.setCancelable(false)
        pd.setCanceledOnTouchOutside(false)
        isCancelable = false
        return pd
    }
}