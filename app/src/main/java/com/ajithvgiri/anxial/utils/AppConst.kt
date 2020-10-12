package com.ajithvgiri.anxial.utils

import android.content.Context
import android.widget.Toast
import timber.log.Timber

class AppConst {
    companion object {
        const val PREFERENCE_TOKEN = "token"
    }
}

fun Context.showError(error: String) {
//    Toast.makeText(applicationContext, error, Toast.LENGTH_SHORT).show()
    Timber.e(Exception(error))
}