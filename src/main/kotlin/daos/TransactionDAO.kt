package com.teya.daos

import com.teya.domain.Transaction
import java.math.BigDecimal

interface TransactionDAO {
    fun createTransaction(
        accountId: String,
        counterpartyId: String,
        amount: BigDecimal,
        transactionType: Transaction.TransactionType,
    ): Transaction

    fun getTransactions(accountId: String): List<Transaction>

    fun reverseTransaction(transactionId: String): Transaction
}