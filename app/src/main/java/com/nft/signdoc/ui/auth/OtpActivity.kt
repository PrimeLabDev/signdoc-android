package com.nft.signdoc.ui.auth

import android.os.Bundle
import com.nft.signdoc.databinding.ActivityOtpBinding
import com.nft.signdoc.extensions.viewBinding
import com.nft.signdoc.ui.base.BaseActivity




class OtpActivity : BaseActivity() {

    private val binding by viewBinding(ActivityOtpBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        listenToViewEvents()

    }

    private fun listenToViewEvents() {

    }
}