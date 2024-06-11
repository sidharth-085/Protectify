package com.sid.protectify.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.material.snackbar.Snackbar
import com.sid.protectify.R
import com.sid.protectify.ViewModel.SignInViewModel
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private val signInViewModel: SignInViewModel by viewModels()
    private lateinit var googleAuthUiClient: GoogleAuthUiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val oneTapClient = Identity.getSignInClient(this)
        googleAuthUiClient = GoogleAuthUiClient(this, oneTapClient)

        val signInButton: Button = findViewById(R.id.sign_in_button)
        signInButton.setOnClickListener {
            signIn()
        }

        observeViewModel()
    }

    override fun onStart() {
        super.onStart()

        if (googleAuthUiClient.getSignedInUser() != null) {
            navigateToMainActivity()
        }
    }

    private fun signIn() {
        lifecycleScope.launch {
            val intentSender = googleAuthUiClient.signIn()
            intentSender?.let {
                startIntentSenderForResult(
                    it,
                    REQ_ONE_TAP,
                    null,
                    0,
                    0,
                    0
                )
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            signInViewModel.state.collect { state ->
                if (state.isSignInSuccessful) {
                    showToast("Sign in Successful!")

                    navigateToMainActivity()
                    signInViewModel.resetState()
                }
                else if (state.signInError != null) {
                    showToast("Sign in Failed!")
                }
            }
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_ONE_TAP) {
            lifecycleScope.launch {
                data?.let {
                    val result = googleAuthUiClient.signInWithIntent(it)
                    signInViewModel.signInResult(result)
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val REQ_ONE_TAP = 2
    }
}