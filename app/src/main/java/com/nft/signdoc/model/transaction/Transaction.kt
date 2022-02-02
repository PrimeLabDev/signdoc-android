package com.nft.signdoc.model.transaction

import com.nft.signdoc.data.networks.response.DtoCounterParty
import org.ocpsoft.prettytime.PrettyTime
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId

import java.util.*
import com.nft.signdoc.data.networks.response.DtoTransactionResponse
import org.threeten.bp.Instant


data class Transaction(
    val id: String,
    val counterParty: List<DtoCounterParty>,
    val direction: TransactionDirection,
    val timestamp: LocalDateTime
) {
    fun identifier() = "#$id"
    fun getPrettyTime(): String {
        return PrettyTime().format(
            Date(
                timestamp.atZone(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli()
            )
        )
    }
}

fun DtoTransactionResponse.toDomainModel() = Transaction(
    id = transactionId,
    counterParty = counterParty ?: listOf(),
    direction = if (sender == true) TransactionDirection.Outgoing else TransactionDirection.Incoming,
    timestamp = Instant.ofEpochMilli(created ?: System.currentTimeMillis())
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()
)

fun DtoCounterParty.toDomainModel() = CounterParty(
    walletId = walletId,
    name = fullName
)