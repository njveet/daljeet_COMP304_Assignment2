package com.example.daljeet_comp304lab2_ex1.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TaskCreationScreen(
    onTaskCreated: (String, String, String) -> Unit,  // Expect task details (title, description, dueDate)
    onCancel: () -> Unit  // Callback for canceling the task creation
) {
    val titleState = remember { mutableStateOf("") }
    val descriptionState = remember { mutableStateOf("") }
    val dueDateState = remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = titleState.value,
            onValueChange = { titleState.value = it },
            label = { Text("Task Title") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = descriptionState.value,
            onValueChange = { descriptionState.value = it },
            label = { Text("Description") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = dueDateState.value,
            onValueChange = { dueDateState.value = it },
            label = { Text("Due Date") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Button(onClick = { onTaskCreated(titleState.value, descriptionState.value, dueDateState.value) }) {
                Text("Create Task")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { onCancel() }) {
                Text("Cancel")
            }
        }
    }
}
