package com.example.task.service

import com.example.task.model.TaskDTO
import com.example.task.repository.TaskRepositoryImpl
import com.example.task.repository.TasksRepository

class TaskService(private val repository: TasksRepository) {

    suspend fun allTasks() = repository.getAllTasks()

    suspend fun createTask(task: TaskDTO) = repository.postCreateTask(task)

    suspend fun editTask(id: Int, task: TaskDTO) = repository.putEditTask(id, task)

    suspend fun deleteTask(id: Int) = repository.deleteTask(id)
}