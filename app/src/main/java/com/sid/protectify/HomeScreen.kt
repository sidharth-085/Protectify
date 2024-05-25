package com.sid.protectify

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_screen)

        val bottomNavigationBar = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationBar.setOnItemSelectedListener { menuItem ->
            if (menuItem.itemId == R.id.nav_guard) {
                inflateFragment()
            }

            true
        }
    }

    private fun inflateFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, GuardFragment.newInstance())
        transaction.commit()
    }
}