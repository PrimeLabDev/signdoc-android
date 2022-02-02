package com.nft.signdoc.data.networks.request

import com.google.gson.annotations.SerializedName

data class DtoRefreshTokenRequest(
    @SerializedName("walletName")
    val walletName:String,
    @SerializedName("refreshToken")
    val refreshToken:String
)
