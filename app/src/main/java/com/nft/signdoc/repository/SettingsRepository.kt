package com.nft.signdoc.repository

import com.nft.signdoc.extensions.safeCall
import com.nft.signdoc.data.networks.Api
import com.nft.signdoc.data.networks.request.DtoChangeWalletRequest
import com.nft.signdoc.data.networks.request.DtoUserCreateRequest
import com.nft.signdoc.data.preference.SharePrefs
import com.nft.signdoc.model.toDomain
import com.nft.signdoc.model.toDomainModel

class SettingsRepository(private val api: Api, private val sharePrefs: SharePrefs) {


    suspend fun changeWallet(request: DtoChangeWalletRequest) = safeCall {
//        val response = api.changeWallet(request)
//        response.isSuccessful
        true
    }

    suspend fun addWallet(name: String) = safeCall {
//        val response = api.addWallet(DtoAddWalletRequest(name))
//        response.isSuccessful
        true
    }
    suspend fun getUserProfile(userId: String) = safeCall {
        val dtoResponse = api.getUserProfile(userId)
        dtoResponse.dtoUserInfo.toDomain()
    }

    suspend fun changeName(name: String, phone: String, email:String) = safeCall {
        val dToUser = DtoUserCreateRequest(name, sharePrefs.walletName, phone, email)
        sharePrefs.userName = name
        api.modifyUser(sharePrefs.userId, dToUser)
    }

    suspend fun changeEmail(email: String, currentPhone: String) = safeCall {
        val dToUser = DtoUserCreateRequest(sharePrefs.userName, sharePrefs.walletName, currentPhone, email)
        api.modifyUser(sharePrefs.userId, dToUser)
//        //if uses phone, just update it
//        if (sharePrefs.loginType == "phone")
//        {
//            api.modifyUser(sharePrefs.userId, dToUser)
//        }
//        else
//        {
//            //send OTP Request then change
//            val request = DtoLoginRequest(sharePrefs.walletName)
//            api.login(request)
//        }
    }

    suspend fun changePhone(phone: String, currentEmail: String) = safeCall {
        //if uses phone, have to do 2 factor
        val dToUser = DtoUserCreateRequest(sharePrefs.userName, sharePrefs.walletName, phone, currentEmail)
        api.modifyUser(sharePrefs.userId, dToUser)
//        if (sharePrefs.loginType == "phone")
//        {
//            //send OTP Request then change
//            val request = DtoLoginRequest(sharePrefs.walletName)
//            api.login(request)
//        }
//        else
//        {
//            api.modifyUser(sharePrefs.userId, dToUser)
//        }
    }
}