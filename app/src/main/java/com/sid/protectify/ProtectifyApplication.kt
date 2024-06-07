package com.sid.protectify

import android.app.Application

class ProtectifyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPref.init(this)
    }
}