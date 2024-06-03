package com.sid.protectify

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val permissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.CAMERA,
        Manifest.permission.READ_CONTACTS
    )

    private val permissionCode = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        askForPermissions()

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
                    inflateFragment(MapsFragment())
                }

                R.id.nav_profile -> {
                    inflateFragment(ProfileFragment.newInstance())
                }
            }
            true
        }

        bottomNavigationBar.selectedItemId = R.id.nav_home
    }

    private fun askForPermissions() {
        ActivityCompat.requestPermissions(this, permissions, permissionCode)
    }

    private fun inflateFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == permissionCode) {
            if (allPermissionsGranted()) {

            }
            else {

            }
        }
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivity(intent)
    }

    private fun allPermissionsGranted(): Boolean {
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }

        return true
    }
}