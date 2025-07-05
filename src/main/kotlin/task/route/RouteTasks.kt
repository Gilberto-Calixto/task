package com.example.task.route

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.task.model.TaskDTO
import com.example.task.repository.TaskRepositoryImpl
import com.example.task.service.TaskService
import com.example.task.usecase.TaskUseCase
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.tasksRoute() {

    val repository = TaskRepositoryImpl()
    val service = TaskService(repository)
    val useCase = TaskUseCase(service)

    route("/tasks") {

        get {

            val response = useCase.tasks()

            call.respond(mapOf("tasks" to response))

        }

        post {

            val request = call.receive<TaskDTO>()

            val response = useCase.create(request)

            response.takeIf { it > 0 }
                ?.let {
                    call.respond(HttpStatusCode.Created)
                } ?: call.respond(HttpStatusCode.BadRequest)
        }

        put("/{id}") {

            val id = call.parameters ["id"]?.toIntOrNull() ?: return@put call.respond(HttpStatusCode.BadRequest)
            val request = call.receive<TaskDTO>()

            val response = useCase.edit(id, request)
            response.takeIf { it > 0 }
                ?.let {
                    call.respond(HttpStatusCode.OK)
                } ?: call.respond(HttpStatusCode.BadRequest)
        }

        delete("/{id}") {

            val id = call.parameters["id"]?.toIntOrNull() ?: return@delete call.respond(HttpStatusCode.BadRequest)

            val response = useCase.delete(id)
            response.takeIf { it > 0 }
                ?.let {
                    call.respond(HttpStatusCode.OK)
                } ?: call.respond(HttpStatusCode.BadRequest)
        }
    }

    route("/login") {

        post {

//            val token = JWT.create()
//                .withAudience("")
//                .withIssuer("")
//                .sign(Algorithm.HMAC256(""))
//                .withClaim("")
//                .withExpiresAt("")
//
//
        }
    }
}






