package com.teya.routes

import com.teya.domain.Transaction
import com.teya.domain.Transaction.TransactionType
import com.teya.services.TransactionService
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject
import java.math.BigDecimal
import java.math.RoundingMode

fun Routing.transactionRoutes() {
    val transactionService: TransactionService by application.inject()

    route("/accounts/{accountId}/transactions") {
        get {
            val accountId = call.parameters["accountId"] ?: throw NotFoundException("Account ID is required")
            val transactions = transactionService.getTransactions(accountId)
            call.respond(transactions.map { TransactionResponse(it) })
        }
    }

    route("/transactions") {
        post("/move-money") {
            val payload = call.receive<MovementPayload>()
            val transactions = transactionService.moveMoney(
                payload.senderAccountId,
                payload.receiverAccountId,
                BigDecimal(payload.amount).setScale(2, RoundingMode.FLOOR)
            )
            call.respond(transactions.map { TransactionResponse(it) })
        }

        post("/reverse") {
            val payload = call.receive<List<TransactionReversal>>()
            val reversedTransactions = transactionService.reverseTransactions(payload.map { it.transactionId })
            call.respond(reversedTransactions.map { TransactionResponse(it) })
        }
    }
}

@Serializable
data class TransactionReversal(
    val transactionId: String
)

@Serializable
data class MovementPayload(
    val senderAccountId: String,
    val receiverAccountId: String,
    val amount: String
)

@Serializable
data class TransactionResponse(
    val transactionId: String,
    val transactionType: TransactionType,
    val amount: String,
    val accountId: String,
    val counterpartyId: String,
    val status: Transaction.TransactionStatus
) {
    constructor(transaction: Transaction) : this(
        transactionId = transaction.transactionId,
        transactionType = transaction.transactionType,
        amount = transaction.amount.setScale(2).toPlainString(),
        accountId = transaction.accountId,
        counterpartyId = transaction.counterpartyId,
        status = transaction.status
    )
}