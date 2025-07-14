package com.example.database

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database

fun Application.connectDatabase() {

    val dbUrl = System.getenv("DB_URL") ?: error("Missing DB_URL")
    println("Connecting to database at $dbUrl")
    val dbUser = System.getenv("DB_USER") ?: error("Missing DB_USER")
    val dbPassword = System.getenv("DB_PASSWORD") ?: error("Missing DB_PASSWORD")
    val dbDriver = System.getenv("DB_DRIVER") ?: error("Missing DB_DRIVER")

    Database.connect(dbUrl, dbDriver, dbUser, dbPassword)
}