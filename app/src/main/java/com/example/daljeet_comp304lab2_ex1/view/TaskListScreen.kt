package com.example.daljeet_comp304lab2_ex1.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.daljeet_comp304lab2_ex1.model.TaskEntity
import com.example.daljeet_comp304lab2_ex1.ui.components.TaskItem
import com.example.daljeet_comp304lab2_ex1.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    viewModel: TaskViewModel,
    onAddTask: () -> Unit,
    onTaskClick: (TaskEntity) -> Unit,
    onCompleteTask: (TaskEntity) -> Unit,
    onPendingTask: (TaskEntity) -> Unit,
    onClearCompletedTasks: () -> Unit  // Adding parameter for clearing completed tasks
) {
    val tasks by viewModel.tasks.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Task List") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent), // Transparent top bar
                actions = {
                    Button(onClick = onClearCompletedTasks) {
                        Text("Clear Completed", color = Color.Black) // Black text for the button
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddTask) {
                Icon(Icons.Filled.Add, contentDescription = "Add Task")
            }
        },
        containerColor = Color.Transparent // Transparent background for the scaffold
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(tasks) { task ->
                TaskItem(
                    task = task,
                    onTaskClick = onTaskClick,
                    onCompleteTask = onCompleteTask,
                    onPendingTask = onPendingTask
                )
            }
        }
    }
}
