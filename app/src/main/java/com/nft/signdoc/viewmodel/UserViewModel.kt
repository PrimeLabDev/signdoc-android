package com.nft.signdoc.viewmodel

import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModel
import com.nft.signdoc.data.preference.SharePrefs
import com.nft.signdoc.extensions.resultFlow
import com.nft.signdoc.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: Repository, sharePrefsRepository: SharePrefs) : ViewModel() {
    var currentEmail: String = ""
    var currentPhone: String = ""
    var usesPhone: Boolean = false
    var walletName = sharePrefsRepository.walletName
    val loginType = sharePrefsRepository.loginType
    val loggedIn = sharePrefsRepository.accessToken.isNotEmpty() || sharePrefsRepository.accessToken.isNotBlank()

    fun createUser(name: String, walletId: String, claimNFTID: String? = null) = resultFlow {
        repository.createUser(name, walletId, currentPhone, currentEmail, claimNFTID)
    }

    fun loginUser(walletName: String) = resultFlow {
        repository.login(walletName)
    }

    fun verifyUser(walletName: String, nonce: String) = resultFlow {
        repository.verifyLogin(walletName, nonce)
    }

    fun updateUser(userId: String) = resultFlow {
        repository.modifyUser(userId, currentPhone, currentEmail)
    }

    fun getAllDocuments() = resultFlow {
        repository.getAllDocuments()
    }


}