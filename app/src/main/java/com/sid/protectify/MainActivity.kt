package com.sid.protectify

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val bottomNavigationBar = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationBar.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    inflateFragment(HomeFragment.newInstance())
                }

                R.id.nav_guard -> {
                    inflateFragment(GuardFragment.newInstance())
                }

                R.id.nav_dashboard -> {
                    inflateFragment(DashboardFragment.newInstance())
                }

                R.id.nav_profile -> {
                    inflateFragment(ProfileFragment.newInstance())
                }
            }
            true
        }

        bottomNavigationBar.selectedItemId = R.id.nav_home
    }

    private fun inflateProfileFragment() {

    }

    private fun inflateDashboardFragment() {

    }

    private fun inflateFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}