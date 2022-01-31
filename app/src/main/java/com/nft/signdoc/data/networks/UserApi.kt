package com.nft.signdoc.data.networks

import com.nft.signdoc.data.networks.request.DtoRefreshTokenRequest
import com.nft.signdoc.data.networks.request.DtoUserCreateNFTRequest
import com.nft.signdoc.data.networks.request.DtoUserCreateRequest
import com.nft.signdoc.data.networks.response.DtoRefreshTokenResponse
import com.nft.signdoc.data.networks.response.DtoUserInfoResponse
import com.nft.signdoc.data.networks.response.DtoUserProfileResponse
import retrofit2.http.*

interface UserApi {
    @GET("users/{user_id}")
    suspend fun getUserProfile(@Path("user_id") userId: String): DtoUserProfileResponse

    @PUT("{user_id}")
    suspend fun modifyUser(@Path("user_id") userId: String): DtoUserInfoResponse

    @DELETE("{user_id}")
    suspend fun deleteUser(@Path("user_id") userId: String): DtoUserInfoResponse

    @GET("user/{user_id}/resend_code")
    suspend fun resendCode(@Path("user_id") userId: String): DtoUserInfoResponse

    @POST("user/create")
    suspend fun createUser(@Body request: DtoUserCreateRequest): DtoUserInfoResponse

    @POST("user/create")
    suspend fun createUser(@Body request: DtoUserCreateNFTRequest): DtoUserInfoResponse

    @POST("user/suggest/?walletName=moisesmarques.near&suggestionCount=10")
    suspend fun suggestWalletName(@Body request: DtoUserCreateRequest): DtoUserInfoResponse

    @POST("users/refresh")
    suspend fun refreshToken(@Body requestDto: DtoRefreshTokenRequest): DtoRefreshTokenResponse
}