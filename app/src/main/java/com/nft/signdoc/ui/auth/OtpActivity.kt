package com.nft.signdoc.ui.auth

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import com.nft.signdoc.R
import com.nft.signdoc.databinding.ActivityOtpBinding
import com.nft.signdoc.extensions.observeResultFlow
import com.nft.signdoc.extensions.viewBinding
import com.nft.signdoc.ui.base.BaseActivity
import com.nft.signdoc.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtpActivity : BaseActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, OtpActivity::class.java)
        }
    }

    private val binding by viewBinding(ActivityOtpBinding::inflate)
    private val userViewModel : UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        listenToViewEvents()

        if (userViewModel.loginType == "email") {
            binding.sentToMethod.text = getString(R.string.sent_code_email_current)
        } else {
            binding.sentToMethod.text = getString(R.string.sent_code_phone_current)
        }

    }

    private fun listenToViewEvents() {

        binding.edt1.setOnFocusCleared(0)
        binding.edt2.setOnFocusCleared(1)
        binding.edt3.setOnFocusCleared(2)
        binding.edt4.setOnFocusCleared(3)
        binding.edt5.setOnFocusCleared(4)
        binding.edt6.setOnFocusCleared(5)
        binding.btnContinue.setOnClickListener { view ->
            val nonce = StringBuilder()
                .append(binding.edt1.text.toString())
                .append(binding.edt2.text.toString())
                .append(binding.edt3.text.toString())
                .append(binding.edt4.text.toString())
                .append(binding.edt5.text.toString())
                .append(binding.edt6.text.toString())
                .toString()
            observeResultFlow(
                userViewModel.verifyUser(
                    userViewModel.walletName,
                    nonce
                ), successHandler = {
                    //intent go to Home verification was successful
                }, errorHandler = {
                    Toast.makeText(this, it?.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }, httpErrorHandler = {
                    Toast.makeText(this, it?.message, Toast.LENGTH_SHORT).show()
                })
        }

        binding.resendCodeText.setOnClickListener {
            userViewModel.loginUser(userViewModel.walletName)
            Toast.makeText(
                this,
                getString(R.string.resend_code_confirmation),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    //
    fun AppCompatEditText.setOnFocusCleared(index: Int) {


        this.setOnClickListener {
            it.requestFocus()
        }
        this.doAfterTextChanged {
            it.checkAndHandle(index)
        }
        this.setOnKeyListener { v, keyCode, event ->
            if (event.keyCode == KeyEvent.KEYCODE_DEL) {
                if (getEditText(index).text?.isNotEmpty() == true) {
                    getEditText(index).text?.clear()
                    getEditText(index).requestFocus()
                } else {
                    if (index != 0) {
                        getEditText(index -1).requestFocus()
                    }
                }
                true
            } else {
                false
            }

        }
    }


    private fun Editable?.checkAndHandle(index: Int) {
        if (!this.isNullOrEmpty()) {

            if (index < 5) {
                getEditText(index + 1).requestFocus()
            } else {
                getEditText(5).clearFocus()

            }
        }

        if (index == 5) {
            binding.btnContinue.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    this@OtpActivity,
                    if (this.isNullOrBlank()) R.color.btndisabled_color else R.color.dark_blue
                )
            )
        }
    }

    private fun getEditText(index: Int): AppCompatEditText {
        return when (index) {
            0 -> {
                binding.edt1
            }
            1 -> {
                binding.edt2
            }
            2 -> {
                binding.edt3
            }
            3 -> {
                binding.edt4
            }
            4 -> {
                binding.edt5
            }
            5 -> {
                binding.edt6
            }


            else -> {
                binding.edt6
            }
        }
    }
}