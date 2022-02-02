package com.nft.signdoc.data.networks.request

import com.nft.signdoc.model.Wallet

data class DtoChangeWalletRequest(
    val id: Int
)

fun Wallet.toRequest() = kotlin.run {
    DtoChangeWalletRequest(id)
}