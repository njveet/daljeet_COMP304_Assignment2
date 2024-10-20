package com.example.daljeet_comp304lab2_ex1.repository

import android.content.Context
import com.example.daljeet_comp304lab2_ex1.database.TaskDatabase
import com.example.daljeet_comp304lab2_ex1.model.TaskEntity
import kotlinx.coroutines.flow.Flow

class TaskRepository(context: Context) {
    private val taskDao = TaskDatabase.getDatabase(context).taskDao()

    fun getAllTasks(): Flow<List<TaskEntity>> {
        return taskDao.getAllTasks()
    }

    suspend fun insertTask(task: TaskEntity) {
        taskDao.insertTask(task)
    }

    suspend fun updateTask(task: TaskEntity) {
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: TaskEntity) {
        taskDao.deleteTask(task)
    }

    // New method to delete completed tasks
    suspend fun deleteCompletedTasks() {
        taskDao.deleteCompletedTasks()  // Deletes all tasks marked as completed
    }
}
