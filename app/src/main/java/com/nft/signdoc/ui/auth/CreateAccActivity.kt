package com.nft.signdoc.ui.auth

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.nft.signdoc.R
import com.nft.signdoc.databinding.ActivityCreateAccountBinding
import com.nft.signdoc.extensions.makeLinks
import com.nft.signdoc.extensions.viewBinding
import com.nft.signdoc.ui.base.BaseActivity
import com.nft.signdoc.ui.base.BaseFragment
import com.nft.signdoc.util.Helpers
import com.nft.signdoc.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CreateAccActivity : BaseActivity() {


    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, CreateAccActivity::class.java)
        }
    }

    private val binding by viewBinding(ActivityCreateAccountBinding::inflate)

    private val userViewModel : UserViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
        listenToViewEvents()
    }

    private fun initViews(){

        userViewModel.prefs.clear()

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

    }

    private fun listenToViewEvents() {
        Log.e("prefs ", "result : "+ userViewModel.currentEmail)

        binding.emailBtn.setOnClickListener {
            binding.EmailPhone.text?.clear()
            binding.emailBtn.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.selectedColor))
            binding.phoneBtn.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
            binding.EmailPhone.hint = this.getString(R.string.email_example)
            userViewModel.usesPhone = false
            binding.EmailPhone.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            binding.signUpMethod.text = getString(R.string.email)

        }

        binding.phoneBtn.setOnClickListener {
            binding.EmailPhone.text?.clear()
            binding.signUpMethod.text = getString(R.string.phone)
            binding.phoneBtn.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.selectedColor))
            binding.emailBtn.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
            binding.EmailPhone.hint = this.getString(R.string.phone_example)
            binding.EmailPhone.inputType = InputType.TYPE_CLASS_PHONE
            userViewModel.usesPhone = true
        }

        binding.loginButtonView.setOnClickListener {
            startActivity(LoginActivity.getIntent(this))
        }

        binding.EmailPhone.doAfterTextChanged {
            checkContinue(it)
        }

        binding.btnCreateAccount.setOnClickListener {
            getStarted()
        }
    }

    @SuppressLint("TimberArgCount")
    private fun getStarted() {
        val usesEmail = !userViewModel.usesPhone
        if (Helpers.checkEmailPhone(binding.EmailPhone.text.toString(), usesEmail)) {
            if (userViewModel.usesPhone) {
                userViewModel.currentPhone = binding.EmailPhone.text.toString()
                userViewModel.prefs.currentPhone = binding.EmailPhone.text.toString()
            } else {
                userViewModel.currentEmail = binding.EmailPhone.text.toString()
                userViewModel.prefs.currentEmail = binding.EmailPhone.text.toString()
            }

            userViewModel.apply {

            }

            startActivity(SignupActivity.getIntent(this))
        } else {
            if (usesEmail) {
                Toast.makeText(this, getString(R.string.email_error), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, getString(R.string.phone_error), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkContinue(it : Editable?){
        binding.btnCreateAccount.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                this,
                if (it.isNullOrBlank()) R.color.btndisabled_color else R.color.dark_blue
            )
        )
    }
}