package com.teya.daos.memory

import com.teya.daos.TransactionDAO
import com.teya.db.TransactionsDB
import com.teya.domain.Transaction
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
            counterpartyId = counterpartyId
        )

        transactionsDB.addTransaction(newTransaction)
        return newTransaction
    }

    override fun getTransactions(accountId: String): List<Transaction> =
        transactionsDB.transactions.filter { it.accountId == accountId }

    override fun reverseTransaction(transactionId: String): Transaction {
        TODO("Not yet implemented")
    }

}