package com.nft.signdoc.data.networks

import com.nft.signdoc.data.networks.response.DtoBaseResponse
import com.nft.signdoc.data.networks.response.DtoContact
import com.nft.signdoc.model.Contact
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ContactApi {


    @POST("contacts/import")
    suspend fun importContact(@Body contact: List<Contact>): Response<ResponseBody>

    @POST("contacts")
    suspend fun addContact(@Body contact: Contact): Response<ResponseBody>

    @GET("contacts/list/{ownerId}")
    suspend fun getContacts(@Path("ownerId") ownerId: String): DtoBaseResponse<List<DtoContact>>
}