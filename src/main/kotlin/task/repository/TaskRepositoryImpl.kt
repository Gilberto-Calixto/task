package com.example.task.repository

import com.example.entity.TaskTable
import com.example.task.model.Task
import com.example.task.model.TaskDTO
import com.example.task.model.toTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class TaskRepositoryImpl: TasksRepository {
    override suspend fun getAllTasks() = withContext(Dispatchers.IO) {
        transaction {
            TaskTable
                .selectAll()
                .map { it.toTask() }
        }
    }

    override suspend fun postCreateTask(task: TaskDTO) = withContext(Dispatchers.IO) {
        transaction {
            TaskTable
                .insertAndGetId {
                    it[name] = task.name
                    it[description] = task.description
                    it[isCompleted] = task.isCompleted
                }.value
        }
    }

    override suspend fun putEditTask(id: Int, task: TaskDTO) = withContext(Dispatchers.IO) {
        transaction {
            TaskTable
                .update( {TaskTable.id eq id}) {
                    it[name] = task.name
                    it[description] = task.description
                    it[isCompleted] = task.isCompleted
                }
        }
    }

    override suspend fun deleteTask(id: Int) = withContext(Dispatchers.IO) {
        transaction {
            TaskTable.deleteWhere { TaskTable.id eq id }
        }
    }
}
