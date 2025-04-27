package com.example.task.repository

import com.example.task.model.Task
import com.example.task.model.TaskDTO

interface TasksRepository {

    suspend fun getAllTasks(): List<Task>

    suspend fun postCreateTask(task: TaskDTO): Int
}