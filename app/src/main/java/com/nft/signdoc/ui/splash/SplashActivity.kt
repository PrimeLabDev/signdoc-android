package com.nft.signdoc.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.nft.signdoc.R
import com.nft.signdoc.extensions.startNewActivityWithDefaultAnimation
import com.nft.signdoc.ui.auth.CreateAccActivity
import com.nft.signdoc.ui.auth.SignupActivity
import com.nft.signdoc.ui.home.HomeActivity
import com.nft.signdoc.ui.sign.DocSignActivity
import com.nft.signdoc.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val userViewModel : UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if(!userViewModel.loggedIn){
            startNewActivityWithDefaultAnimation(CreateAccActivity.getIntent(this))
        }else{
            startNewActivityWithDefaultAnimation(HomeActivity.getIntent(this))
        }

    }
}