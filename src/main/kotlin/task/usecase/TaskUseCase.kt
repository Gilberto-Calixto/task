package com.example.task.usecase

import com.example.task.model.TaskDTO
import com.example.task.service.TaskService

class TaskUseCase(private val service: TaskService) {

    suspend fun tasks() = service.allTasks()

    suspend fun create(task: TaskDTO) = service.createTask(task)

    suspend fun edit(id: Int, task: TaskDTO) = service.editTask(id, task)

    suspend fun delete(id: Int) = service.deleteTask(id)

}