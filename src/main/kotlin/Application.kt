package com.example

import com.example.database.connectDatabase
import com.example.database.migration
import io.ktor.server.application.*

fun main(args: Array<String>) {
    migration()
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {

    connectDatabase()

}
