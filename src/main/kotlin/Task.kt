package com.example

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
