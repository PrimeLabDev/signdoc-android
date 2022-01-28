package com.nft.signdoc.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nft.signdoc.R
import com.nft.signdoc.extensions.startNewActivityWithDefaultAnimation
import com.nft.signdoc.ui.auth.SignupActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startNewActivityWithDefaultAnimation(SignupActivity.getIntent(this))
    }
}