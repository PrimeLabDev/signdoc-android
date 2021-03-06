package com.nft.signdoc.data.networks

import com.google.gson.JsonObject
import com.nft.signdoc.data.networks.request.DtoAddWalletRequest
import com.nft.signdoc.data.networks.request.DtoChangeWalletRequest
import com.nft.signdoc.data.networks.request.DtoLoginRequest
import com.nft.signdoc.data.networks.request.DtoUserCreateRequest
import com.nft.signdoc.data.networks.response.DtoLoginResponse
import com.nft.signdoc.data.networks.response.DtoUserInfoResponse
import com.nft.signdoc.data.networks.response.DtoUserProfileResponse
import com.nft.signdoc.data.networks.response.DtoWalletResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface Api {
    @GET("api/users")
    suspend fun sampleGet(): JsonObject

    @GET("api/wallets")
    suspend fun getWallets(): DtoWalletResponse

    @POST("api/change_wallet")
    suspend fun changeWallet(@Body request: DtoChangeWalletRequest): Response<ResponseBody>

    @POST("api/add_wallet")
    suspend fun addWallet(@Body request: DtoAddWalletRequest): Response<ResponseBody>

    //https://api.nearlogin.io/users/2slqFE0gPeySpwnPKHn00

    @GET("users/{user_id}")
    suspend fun getUserProfile(@Path("user_id") userId: String): DtoUserProfileResponse

    @PUT("users/{user_id}")
    suspend fun modifyUser(@Path("user_id") userId: String, @Body request: DtoUserCreateRequest): DtoUserInfoResponse

    @POST("login")
    suspend fun login(@Body walletName : DtoLoginRequest): DtoLoginResponse

}