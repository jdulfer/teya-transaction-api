package com.teya.daos.memory

import com.teya.daos.TransactionDAO
import com.teya.db.TransactionsDB
import com.teya.domain.Transaction
import io.ktor.server.plugins.*
import java.math.BigDecimal

class MemoryTransactionDAO(private val transactionsDB: TransactionsDB) : TransactionDAO {
    override fun createTransaction(
        accountId: String,
        counterpartyId: String,
        amount: BigDecimal,
        transactionType: Transaction.TransactionType
    ): Transaction {
        val newTransaction = Transaction(
            transactionId = (transactionsDB.transactions.size + 1).toString(),
            transactionType = transactionType,
            amount = amount,
            accountId = accountId,
            counterpartyId = counterpartyId,
            status = Transaction.TransactionStatus.PENDING
        )

        transactionsDB.addTransaction(newTransaction)
        return newTransaction
    }

    override fun getTransactions(accountId: String): List<Transaction> =
        transactionsDB.transactions.filter { it.accountId == accountId }

    override fun reverseTransaction(transactionId: String): Transaction {
        val badTransaction = transactionsDB.transactions.find { it.transactionId == transactionId }
            ?: throw NotFoundException("Transaction not found")

        badTransaction.status = Transaction.TransactionStatus.FAILED

        val reverseTransaction = Transaction(
            transactionId = (transactionsDB.transactions.size + 1).toString(),
            transactionType = if (badTransaction.transactionType == Transaction.TransactionType.INCOMING) {
                Transaction.TransactionType.OUTGOING
            } else {
                Transaction.TransactionType.INCOMING
            },
            amount = badTransaction.amount,
            accountId = badTransaction.accountId,
            counterpartyId = badTransaction.counterpartyId,
            status = Transaction.TransactionStatus.REVERSAL
        )
        transactionsDB.addTransaction(reverseTransaction)
        return reverseTransaction
    }

}