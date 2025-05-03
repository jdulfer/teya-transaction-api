package com.teya.di

import com.teya.daos.TransactionDAO
import com.teya.daos.memory.MemoryTransactionDAO
import com.teya.db.TransactionsDB
import com.teya.services.TransactionService
import io.ktor.client.engine.*
import io.ktor.client.engine.cio.*
import org.koin.dsl.module

val appModule = module {
    factory<HttpClientEngine> { CIO.create() }

    single<TransactionDAO> { MemoryTransactionDAO(get()) }
    single { TransactionsDB() }
    single { TransactionService(get()) }
}