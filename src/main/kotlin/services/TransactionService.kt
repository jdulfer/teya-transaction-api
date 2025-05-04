package com.teya.services

import com.teya.daos.TransactionDAO
import com.teya.domain.Transaction
import java.math.BigDecimal

class TransactionService(private val transactionDAO: TransactionDAO) {
    fun moveMoney(senderAccountId: String, receiverAccountId: String, amount: BigDecimal): List<Transaction> {
        val outgoingTransaction = transactionDAO.createTransaction(
            accountId = senderAccountId,
            counterpartyId = receiverAccountId,
            amount = amount,
            transactionType = Transaction.TransactionType.OUTGOING
        )
        val incomingTransaction = transactionDAO.createTransaction(
            accountId = receiverAccountId,
            counterpartyId = senderAccountId,
            amount = amount,
            transactionType = Transaction.TransactionType.INCOMING
        )

        return listOf(outgoingTransaction, incomingTransaction)
    }

    fun getTransactions(accountId: String): List<Transaction> = transactionDAO.getTransactions(accountId)

    fun reverseTransactions(transactionIds: List<String>): List<Transaction> {
        val reversedTransactions = transactionIds.map { transactionId ->
            transactionDAO.reverseTransaction(transactionId)
        }

        return reversedTransactions
    }
}