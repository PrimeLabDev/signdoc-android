package com.nft.signdoc.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.nft.signdoc.databinding.ActivitySignupBinding
import com.nft.signdoc.extensions.setDebouncedClickListener
import com.nft.signdoc.extensions.startActivityWithDefaultAnimation
import com.nft.signdoc.extensions.viewBinding
import com.nft.signdoc.ui.base.BaseActivity


class SignupActivity : BaseActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, SignupActivity::class.java)
        }
    }

    private val binding by viewBinding(ActivitySignupBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        listenToViewEvents()
    }

    private fun listenToViewEvents() {
        binding.loginButtonView.setDebouncedClickListener {
            startActivityWithDefaultAnimation(LoginActivity.getIntent(this))
        }
    }
}