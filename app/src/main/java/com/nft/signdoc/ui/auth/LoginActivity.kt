package com.nft.signdoc.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.nft.signdoc.databinding.ActivityLoginBinding
import com.nft.signdoc.extensions.viewBinding
import com.nft.signdoc.ui.base.BaseActivity


class LoginActivity : BaseActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    private val binding by viewBinding(ActivityLoginBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        listenToViewEvents()
    }

    private fun listenToViewEvents() {

    }
}