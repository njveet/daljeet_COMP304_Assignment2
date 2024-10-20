package com.example.daljeet_comp304lab2_ex1.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.daljeet_comp304lab2_ex1.model.TaskEntity // Import TaskEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(task: TaskEntity, onBack: () -> Unit) { // Use TaskEntity instead of Task
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Task Details") },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(text = "Title: ${task.title}", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Description: ${task.description}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Due Date: ${task.dueDate}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
