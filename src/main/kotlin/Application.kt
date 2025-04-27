package com.example

import com.example.database.connectDatabase
import com.example.database.migration
import com.example.plugins.plugins
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

        route("/casas") {

            get {
                val query = transaction {
                    TaskTable.selectAll()
                        .map {
                            Task(
                                id = it[TaskTable.id].value,
                                name = it[TaskTable.name],
                                description = it[TaskTable.description],
                                isCompleted = it[TaskTable.isCompleted]
                            )
                        }
                }

                call.respond(query)
            }

            post {

                val request = call.receive<TaskDTO>()

                val response = transaction {
                    TaskTable.insertAndGetId {
                        it[name] = request.name
                        it[description] = request.description
                        it[isCompleted] = request.isCompleted
                    }.value
                }

                if (response > 0) {
                    call.respond(HttpStatusCode.Created)
                }
            }
        }
    }

}
