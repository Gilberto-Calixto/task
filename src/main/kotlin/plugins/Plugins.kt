package com.example.plugins

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.plugins() {

    install(ContentNegotiation) {
        json()
    }

    install(StatusPages) {

        exception<Throwable> { call, cause ->
            when(cause) {

                is IllegalArgumentException -> call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
}