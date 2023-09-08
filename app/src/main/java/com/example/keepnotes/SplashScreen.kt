package com.example.keepnotes


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//insert logo in the drawable folder and
// go into manifest file and set the icon and round icon to logo.png


@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    val splashDelay: Long = 2000 // Delay in milliseconds
    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Start a coroutine to handle the delay
        CoroutineScope(Dispatchers.Main).launch {
            delaySplashScreen()
            navigateToMainActivity()
        }
    }

    private suspend fun delaySplashScreen() {
        delay(splashDelay)
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this@SplashScreen, MainActivity::class.java)
        startActivity(intent)
        finish() // Close the splash screen activity

    }

    override fun onPause() {
        super.onPause()
        // Cancel the coroutine if the activity is paused (e.g., user navigates away)
        CoroutineScope(Dispatchers.Main).coroutineContext.cancel()
    }
}