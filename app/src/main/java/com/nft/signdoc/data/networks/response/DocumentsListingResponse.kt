package com.nft.signdoc.data.networks.response

import com.google.gson.annotations.SerializedName

class DocumentsListingResponse (
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: List<DocumentItem>
)


data class DocumentItem (
    @SerializedName("nft_id")
    val nftId: String,
    @SerializedName("updated")
    val updated: Long,
    @SerializedName("status")
    val status: String,
    @SerializedName("created")
    val created: Long,
    @SerializedName("description")
    val description: String,
    @SerializedName("owner_id")
    val ownerId: String,
    @SerializedName("file_url")
    val fileUrl: String,
    @SerializedName("title")
    val title: String,
)