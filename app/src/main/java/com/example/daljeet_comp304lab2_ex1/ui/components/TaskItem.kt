package com.example.daljeet_comp304lab2_ex1.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.daljeet_comp304lab2_ex1.model.TaskEntity

@Composable
fun TaskItem(
    task: TaskEntity,
    onTaskClick: (TaskEntity) -> Unit,
    onCompleteTask: (TaskEntity) -> Unit,
    onPendingTask: (TaskEntity) -> Unit
) {
    // Set the background color based on whether the task is complete or pending
    val backgroundColor = if (task.isComplete) Color(0xFFD3E8D3) else Color(0xFFFFD3D3)

    // Create a card to display task information
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onTaskClick(task) },  // When the card is clicked, trigger the task click action
        colors = CardDefaults.cardColors(containerColor = backgroundColor),  // Set the card's background color
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Display the task's title, description, and due date
            Text(text = task.title, style = MaterialTheme.typography.titleMedium)
            Text(text = task.description, style = MaterialTheme.typography.bodyMedium)
            Text(text = "Due: ${task.dueDate}", style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(8.dp))

            // Display a button to mark the task as complete or pending, based on its current state
            if (task.isComplete) {
                Button(
                    onClick = { onPendingTask(task) },
                    modifier = Modifier.padding(top = 8.dp),
                    shape = RoundedCornerShape(8.dp),  // Rounded rectangular button
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
                ) {
                    Text(text = "Mark as Pending", color = Color.White)
                }
            } else {
                Button(
                    onClick = { onCompleteTask(task) },
                    modifier = Modifier.padding(top = 8.dp),
                    shape = RoundedCornerShape(8.dp),  // Rounded rectangular button
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
                ) {
                    Text(text = "Mark as Complete", color = Color.White)
                }
            }
        }
    }
}
