package com.nft.signdoc.data.networks

import com.nft.signdoc.data.networks.request.DtoSendTransactionRequest
import com.nft.signdoc.data.networks.response.DtoBaseResponse
import com.nft.signdoc.data.networks.response.DtoTransactionResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TransactionApi {

    @GET("transactions/list/{user_id}")
    suspend fun getTransaction(@Path("user_id") user_id :String): DtoBaseResponse<List<DtoTransactionResponse>>

    @POST("transactions")
    suspend fun sendTransaction(@Body request: DtoSendTransactionRequest): Response<ResponseBody>
}