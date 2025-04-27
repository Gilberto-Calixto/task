package com.example

import com.example.database.connectDatabase
import com.example.database.migration
import com.example.entity.TaskTable
import com.example.plugins.plugins
import com.example.task.model.Task
import com.example.task.model.TaskDTO
import com.example.task.route.tasksRoute
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>) {
    migration()
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    plugins()
    connectDatabase()

    routing {

        tasksRoute()


    }

}
