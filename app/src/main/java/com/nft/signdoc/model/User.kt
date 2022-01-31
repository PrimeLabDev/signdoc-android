package com.nft.signdoc.model

import com.nft.signdoc.data.networks.response.DtoUserResponse


data class User(
    val id: String,
    val walletId: String,
    val name: String,
    val email: String,
    val phone: String,
    val verified: Boolean
)

fun DtoUserResponse.toDomain() = User(
    id = id,
    walletId = walletId,
    name = fullName.orEmpty(),
    email = email.orEmpty(),
    phone = phone.orEmpty(),
    verified = verified ?: false
)