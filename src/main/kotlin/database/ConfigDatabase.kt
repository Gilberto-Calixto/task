package com.example.database

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database

fun Application.connectDatabase() {

    val conf = environment.config

    val url = conf.property("postgres.url").getString()
    val driver = conf.property("postgres.driver").getString()
    val user = conf.property("postgres.user").getString()
    val password = conf.property("postgres.password").getString()

    Database.connect(url, driver, user, password)
}