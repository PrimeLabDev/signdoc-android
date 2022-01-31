package com.nft.signdoc.data.localcontact

import com.nft.signdoc.model.Contact

interface ContactSource {
    suspend fun getAllContactWithEmail(userId: String): List<Contact>
}