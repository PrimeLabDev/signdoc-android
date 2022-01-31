package com.nft.signdoc.data.networks

import com.nft.signdoc.data.networks.request.ClimNFTRequest
import com.nft.signdoc.data.networks.response.DtoBaseResponse
import com.nft.signdoc.data.networks.response.DtoMessageResponse
import com.nft.signdoc.data.networks.response.DtoNFTResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface NFTApi {

    @GET("nfts/list")
    suspend fun getAllNFTCollections(@Query("owner_id") userId: String): DtoBaseResponse<List<DtoNFTResponse>>

    @Multipart
    @POST("nfts")
    suspend fun createNft(
        @Part("data") nftInfo: RequestBody,
        @Part file: MultipartBody.Part? = null
    ): DtoMessageResponse

    @GET("nfts/{nft_id}")
    suspend fun getNFTDetails(@Path("nft_id") nftId: String): DtoBaseResponse<DtoNFTResponse>

    @POST("nfts/{nft_id}/claim")
    suspend fun claimNFT(@Path("nft_id") nftId: String, @Body climNFTRequest: ClimNFTRequest): DtoMessageResponse
}