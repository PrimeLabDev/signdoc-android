package com.nft.signdoc.model

import com.nft.signdoc.data.networks.response.DtoWallet
import com.nft.signdoc.data.networks.response.DtoWalletResponse

data class Wallet(
    val id: Int,
    val address: String,
    val name: String,
    val selected: Boolean
)

fun DtoWallet.toDomainModel() = kotlin.run {
    Wallet(
        id = id,
        name = name.toString(),
        address = address.toString(),
        selected = selected ?: false
    )
}

fun DtoWalletResponse.toDomainModel() = kotlin.run {
    wallets?.map { it.toDomainModel() } ?: emptyList()
}