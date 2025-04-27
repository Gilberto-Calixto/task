package com.example.task.route

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

            val response = service.createTask(request)

            response.takeIf { it > 0 }
                ?.let {
                    call.respond(HttpStatusCode.Created)
                } ?: call.respond(HttpStatusCode.BadRequest)
        }
    }
}