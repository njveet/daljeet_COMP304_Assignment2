package com.example.daljeet_comp304lab2_ex1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daljeet_comp304lab2_ex1.model.TaskEntity
import com.example.daljeet_comp304lab2_ex1.repository.TaskRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    private val _tasks = MutableStateFlow<List<TaskEntity>>(emptyList())
    val tasks: StateFlow<List<TaskEntity>> = _tasks

    init {
        loadTasks()
    }

    private fun loadTasks() {
        viewModelScope.launch {
            repository.getAllTasks().collect { taskList ->
                _tasks.value = taskList
            }
        }
    }

    fun addTask(task: TaskEntity) {
        viewModelScope.launch {
            repository.insertTask(task)
        }
    }

    fun deleteTask(task: TaskEntity) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }

    fun updateTask(task: TaskEntity) {
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }

    // Marks a task as complete
    fun markTaskComplete(task: TaskEntity) {
        viewModelScope.launch {
            repository.updateTask(task.copy(isComplete = true))
        }
    }

    // Marks a task as pending
    fun markTaskPending(task: TaskEntity) {
        viewModelScope.launch {
            repository.updateTask(task.copy(isComplete = false))
        }
    }

    // Clears all completed tasks from the list
    fun clearCompletedTasks() {
        viewModelScope.launch {
            repository.deleteCompletedTasks() // Deletes all completed tasks from repository
            val incompleteTasks = _tasks.value.filter { !it.isComplete }
            _tasks.value = incompleteTasks // Updates the tasks to show only incomplete ones
        }
    }
}
