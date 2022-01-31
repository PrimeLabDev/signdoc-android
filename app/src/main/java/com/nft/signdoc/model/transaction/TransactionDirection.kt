package com.nft.signdoc.model.transaction

sealed class TransactionDirection {
    object Incoming: TransactionDirection()
    object Outgoing: TransactionDirection()
}