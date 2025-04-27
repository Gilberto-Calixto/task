package com.example.task.model

import com.example.entity.TaskTable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.ResultRow

@Serializable
data class Task(
    val id: Int,
    val name: String,
    val description: String,
    @SerialName("is_completed")
    val isCompleted: Boolean = false
)

@Serializable
data class TaskDTO(
    val name: String,
    val description: String,
    @SerialName("is_completed")
    val isCompleted: Boolean = false
)

fun ResultRow.toTask() = Task(
    id = this[TaskTable.id].value,
    name = this[TaskTable.name],
    description = this[TaskTable.description],
    isCompleted = this[TaskTable.isCompleted]
)
