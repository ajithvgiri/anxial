package com.ajithvgiri.anxial.utils

import android.util.Log
import timber.log.Timber


class ReleaseTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }
        // log your crash to your favourite
        // Sending crash report to Firebase CrashAnalytics
//        Crashlytics.log("tag:$tag,\nmessage:$message")
        t?.let {
            ("tag:$tag,\nmessage:${it.message},\nlocalizedMessage:${it.localizedMessage}")
        }
    }
}