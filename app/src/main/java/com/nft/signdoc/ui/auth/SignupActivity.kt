package com.nft.signdoc.ui.auth

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import com.nft.signdoc.databinding.ActivitySignupBinding
import com.nft.signdoc.ui.base.BaseActivity
import com.nft.signdoc.viewmodel.UserViewModel
import android.text.Spannable

import android.text.style.ForegroundColorSpan

import android.text.SpannableString
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import com.nft.signdoc.R
import com.nft.signdoc.extensions.*
import com.nft.signdoc.ui.home.HomeActivity
import com.nft.signdoc.util.AppConstants
import com.nft.signdoc.util.Helpers
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.regex.Pattern


@AndroidEntryPoint
class SignupActivity : BaseActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, SignupActivity::class.java)
        }
    }

    private val binding by viewBinding(ActivitySignupBinding::inflate)

    private val userViewModel : UserViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
        listenToViewEvents()
    }

    private fun initViews(){

        Log.e("prefs ", "result : "+ userViewModel.currentEmail)

        if(!userViewModel.currentEmail.isEmpty()){
            binding.accountId.setText(userViewModel.currentEmail.split("@")[0].replace(".", ""), TextView.BufferType.EDITABLE)
        }else{
            binding.accountId.setText(userViewModel.currentPhone.replace("+", ""), TextView.BufferType.EDITABLE)
        }

        Helpers.setTermsConditions(binding.termsText, this)

        binding.loginButtonView.apply {

            val wordtoSpan: Spannable =
                SpannableString(getString(R.string.already_have_account))

            wordtoSpan.setSpan(
                ForegroundColorSpan(getColor(R.color.dark_blue)),
                27,
                44,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            this.text = wordtoSpan
        }

        binding.fullName.apply {
            doAfterTextChanged {
                checkContinue()
            }

            setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus)
                    binding.fullNameText.setTextColor(ContextCompat.getColor(this@SignupActivity, R.color.dark_blue))
                else
                    binding.fullNameText.setTextColor(ContextCompat.getColor(this@SignupActivity, R.color.gray))
            }
        }

        binding.accountId.apply {
            doAfterTextChanged {
                checkContinue()
            }

            setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus)
                    binding.accountIdText.setTextColor(ContextCompat.getColor(this@SignupActivity, R.color.dark_blue))
                else
                    binding.accountIdText.setTextColor(ContextCompat.getColor(this@SignupActivity, R.color.gray))
            }
        }

    }

    private fun listenToViewEvents() {
        binding.loginButtonView.setDebouncedClickListener {
            startActivityWithDefaultAnimation(LoginActivity.getIntent(this))
        }

        binding.btnCreateAccount.setOnClickListener {

            val pattern = Pattern.compile("^[a-z0-9._-]+$")

            var enteredWalletId = binding.accountId.text?.trim().toString().replace(AppConstants.ACCOUNT_NAME_NEAR_SUFFIX, "")

            if (!enteredWalletId.contains(".near")) {
                enteredWalletId += ".near"
            }

            if (pattern.matcher(enteredWalletId).matches() && !binding.fullName.text?.trim()?.isEmpty()!!) {
                (this@SignupActivity as BaseActivity).showProgressDialog()
                observeResultFlow(
                    userViewModel.createUser(
                        name = binding.fullName.text.toString(),
                        walletId = enteredWalletId
                    ), successHandler = {
                        startActivity(HomeActivity.getIntent(this))
                        dismissProgressDialog()
                    }, errorHandler = {
                        Log.e("hereee","")
                        Toast.makeText(this, it?.message.toString(), Toast.LENGTH_SHORT).show()
                        dismissProgressDialog()
                    }, httpErrorHandler = {
                        if(it?.message?.contains(getString(R.string.account_already_exists), true) == true) {
                            Toast.makeText(this, R.string.account_already_exists, Toast.LENGTH_SHORT).show()
                        } else {
                            Log.e("heree","")
                            Toast.makeText(this, it?.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                        dismissProgressDialog()
                    }
                )
            }
            else if (binding.fullName.text?.trim()?.isEmpty() == true)
            {
                Toast.makeText(
                    this, getString(R.string.full_name_error), Toast.LENGTH_SHORT
                ).show()
            }
            else
                Toast.makeText(
                    this, getString(R.string.wallet_name_error), Toast.LENGTH_SHORT
                ).show()

        }


    }

    private fun checkContinue() {
        binding.btnCreateAccount.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                this,
                if (binding.fullName.text.isNullOrBlank() || binding.accountId.text.isNullOrBlank()) R.color.btndisabled_color else R.color.dark_blue
            )
        )
    }




}