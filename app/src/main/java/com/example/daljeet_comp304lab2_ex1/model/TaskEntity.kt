package com.example.daljeet_comp304lab2_ex1.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String,
    val dueDate: String,
    val isComplete: Boolean = false // New field to store task status
)
