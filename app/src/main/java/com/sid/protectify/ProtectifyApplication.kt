package com.sid.protectify

import android.app.Application
import com.sid.protectify.Constants.SharedPref

class ProtectifyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPref.init(this)
    }
}