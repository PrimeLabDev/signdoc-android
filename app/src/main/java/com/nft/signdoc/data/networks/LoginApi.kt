package com.nft.signdoc.data.networks

import com.nft.signdoc.data.networks.request.DtoLoginRequest
import com.nft.signdoc.data.networks.response.DtoLoginResponse
import com.nft.signdoc.data.networks.response.DtoVerifyLoginResponse
import retrofit2.http.*

interface LoginApi {
    @POST("login")
    suspend fun login(@Body walletName : DtoLoginRequest): DtoLoginResponse

    @POST("login/verify")
    suspend fun verifyLogin(@Body walletName : DtoLoginRequest): DtoVerifyLoginResponse
}