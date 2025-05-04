package com.teya.db

import com.teya.domain.Transaction
import java.math.BigDecimal

class TransactionsDB {
    val transactions = getTransactions()

    fun addTransaction(transaction: Transaction) {
        transactions.add(transaction)
    }
}

fun getTransactions() = mutableListOf(
    Transaction(
        transactionId = "1",
        transactionType = Transaction.TransactionType.OUTGOING,
        amount = BigDecimal("100"),
        accountId = "1",
        counterpartyId = "2",
        status = Transaction.TransactionStatus.COMPLETE
    ),
    Transaction(
        transactionId = "2",
        transactionType = Transaction.TransactionType.INCOMING,
        amount = BigDecimal("100"),
        accountId = "2",
        counterpartyId = "1",
        status = Transaction.TransactionStatus.COMPLETE
    ),
    Transaction(
        transactionId = "3",
        transactionType = Transaction.TransactionType.OUTGOING,
        amount = BigDecimal("13"),
        accountId = "5",
        counterpartyId = "3",
        status = Transaction.TransactionStatus.COMPLETE
    ),
    Transaction(
        transactionId = "4",
        transactionType = Transaction.TransactionType.INCOMING,
        amount = BigDecimal("100"),
        accountId = "3",
        counterpartyId = "5",
        status = Transaction.TransactionStatus.COMPLETE
    ),
    Transaction(
        transactionId = "5",
        transactionType = Transaction.TransactionType.OUTGOING,
        amount = BigDecimal("13596"),
        accountId = "4",
        counterpartyId = "3",
        status = Transaction.TransactionStatus.COMPLETE
    ),
    Transaction(
        transactionId = "6",
        transactionType = Transaction.TransactionType.INCOMING,
        amount = BigDecimal("100"),
        accountId = "3",
        counterpartyId = "4",
        status = Transaction.TransactionStatus.COMPLETE
    ),
    Transaction(
        transactionId = "7",
        transactionType = Transaction.TransactionType.OUTGOING,
        amount = BigDecimal("50"),
        accountId = "2",
        counterpartyId = "4",
        status = Transaction.TransactionStatus.COMPLETE
    ),
    Transaction(
        transactionId = "8",
        transactionType = Transaction.TransactionType.INCOMING,
        amount = BigDecimal("50"),
        accountId = "4",
        counterpartyId = "2",
        status = Transaction.TransactionStatus.COMPLETE
    ),
    Transaction(
        transactionId = "9",
        transactionType = Transaction.TransactionType.OUTGOING,
        amount = BigDecimal("200"),
        accountId = "5",
        counterpartyId = "1",
        status = Transaction.TransactionStatus.COMPLETE
    ),
    Transaction(
        transactionId = "10",
        transactionType = Transaction.TransactionType.INCOMING,
        amount = BigDecimal("200"),
        accountId = "1",
        counterpartyId = "5",
        status = Transaction.TransactionStatus.COMPLETE
    ),
    Transaction(
        transactionId = "11",
        transactionType = Transaction.TransactionType.OUTGOING,
        amount = BigDecimal("75"),
        accountId = "3",
        counterpartyId = "2",
        status = Transaction.TransactionStatus.COMPLETE
    ),
    Transaction(
        transactionId = "12",
        transactionType = Transaction.TransactionType.INCOMING,
        amount = BigDecimal("75"),
        accountId = "2",
        counterpartyId = "3",
        status = Transaction.TransactionStatus.COMPLETE
    )
)