package com.sid.protectify

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isUserLoggedIn = SharedPref.getBoolean(PrefConstants.IS_USER_LOGGED_IN)

        if (isUserLoggedIn) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}