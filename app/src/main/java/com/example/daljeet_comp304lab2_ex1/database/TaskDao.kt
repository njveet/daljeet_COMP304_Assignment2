package com.example.daljeet_comp304lab2_ex1.database

import androidx.room.*
import com.example.daljeet_comp304lab2_ex1.model.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity): Long

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<TaskEntity>>

    // New query to delete all tasks that are marked as completed
    @Query("DELETE FROM tasks WHERE isComplete = 1")
    suspend fun deleteCompletedTasks()  // Deletes all tasks that have been marked as completed
}
