package com.nft.signdoc

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SignDocApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}