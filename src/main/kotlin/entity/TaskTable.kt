package com.example.entity

import org.jetbrains.exposed.dao.id.IntIdTable

object TaskTable: IntIdTable("task") {
    val name = varchar("name", 255)
    val description = text("description")
    val isCompleted = bool("is_completed")
}