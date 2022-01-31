package com.nft.signdoc.model.settings

import com.nft.signdoc.model.Wallet

data class Profile(
    val name: String,
    val email: String,
    val phoneNumber: String,
    val wallet: Wallet,
    val security: Security,
)