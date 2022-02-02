package com.nft.signdoc.ui.auth

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.Toast
import androidx.activity.viewModels
import com.nft.signdoc.databinding.ActivityLoginBinding
import com.nft.signdoc.extensions.viewBinding
import com.nft.signdoc.ui.base.BaseActivity
import com.nft.signdoc.viewmodel.UserViewModel
import androidx.fragment.app.activityViewModels
import com.nft.signdoc.R
import com.nft.signdoc.extensions.observeResultFlow
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber





@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    private val binding by viewBinding(ActivityLoginBinding::inflate)
    private val userViewModel : UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
        listenToViewEvents()
    }

    private fun initViews(){
        binding.createAccountView.apply {
            val wordtoSpan: Spannable =
                SpannableString(getString(R.string.dont_have_account))

            wordtoSpan.setSpan(
                ForegroundColorSpan(getColor(R.color.dark_blue)),
                26,
                40,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            this.text = wordtoSpan
        }
    }

    private fun listenToViewEvents() {

        binding.createAccountView.setOnClickListener{
            onBackPressed()
        }

        binding.btnLogin.setOnClickListener{
          login()
        }
    }

    private fun login() {
        if (binding.accountId.text.toString().isNotBlank()) {
            observeResultFlow(
                userViewModel.loginUser(
                    binding.accountId.text.toString() + ".near"
                ), successHandler = {
                    userViewModel.walletName = binding.accountId.text.toString() + ".near"
                    startActivity(OtpActivity.getIntent(this))
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                }, errorHandler = {
                    Toast.makeText(this, it?.message.toString(), Toast.LENGTH_SHORT).show()
                }, httpErrorHandler = {
                    Timber.e(it.toString())
                    Toast.makeText(this, it?.message.toString(), Toast.LENGTH_SHORT).show()
                }
            )
        } else {
            Toast.makeText(this, getString(R.string.login_text_error), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        startActivity(CreateAccActivity.getIntent(this))
        overridePendingTransition(R.anim.nav_default_enter_anim, R.anim.nav_default_exit_anim)    }
}