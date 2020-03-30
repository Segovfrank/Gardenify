package com.clase.gardenify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setTimer()
    }

    private fun setTimer() {
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
        }, 1000)
    }
}
