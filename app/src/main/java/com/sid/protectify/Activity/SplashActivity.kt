package com.sid.protectify.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sid.protectify.Constants.PrefConstants
import com.sid.protectify.Constants.SharedPref

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