package com.teya.config

import com.teya.di.appModule
import com.teya.routes.helloRoutes
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.module() {
    install(Koin) {
        slf4jLogger()
        modules(
            listOf(
                appModule,
            )
        )
    }

    install(ContentNegotiation) {
        json()
    }

    routing {
        helloRoutes()
    }
}