package com.example.daljeet_comp304lab2_ex1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.daljeet_comp304lab2_ex1.model.TaskEntity
import com.example.daljeet_comp304lab2_ex1.repository.TaskRepository
import com.example.daljeet_comp304lab2_ex1.view.TaskCreationScreen
import com.example.daljeet_comp304lab2_ex1.view.TaskListScreen
import com.example.daljeet_comp304lab2_ex1.viewmodel.TaskViewModel
import com.example.daljeet_comp304lab2_ex1.viewmodel.TaskViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = TaskRepository(applicationContext)
        val taskViewModel: TaskViewModel by viewModels {
            TaskViewModelFactory(repository)
        }

        setContent {
            var textSize by remember { mutableStateOf(16.sp) }
            var isAddTaskVisible by remember { mutableStateOf(false) }

            Surface( // Set the surface to have a transparent background
                modifier = Modifier.fillMaxSize(),
                color = Color.Transparent // Transparent background
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    // Background image
                    Image(
                        painter = painterResource(id = R.drawable.background_image),
                        contentDescription = "Background",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .graphicsLayer {
                                alpha = 1.0f // Full transparency
                            }
                    ) {
                        if (isAddTaskVisible) {
                            TaskCreationScreen(
                                onTaskCreated = { title, description, dueDate ->
                                    val newTask = TaskEntity(
                                        title = title,
                                        description = description,
                                        dueDate = dueDate,
                                        isComplete = false  // Set default to pending when created
                                    )
                                    taskViewModel.addTask(newTask)
                                    isAddTaskVisible = false
                                },
                                onCancel = { isAddTaskVisible = false }
                            )
                        } else {
                            TaskListScreen(
                                viewModel = taskViewModel,
                                onAddTask = {
                                    isAddTaskVisible = true
                                },
                                onTaskClick = { task ->
                                    taskViewModel.updateTask(task.copy(title = "Updated Task"))
                                },
                                onCompleteTask = { task ->
                                    taskViewModel.markTaskComplete(task)  // Marks task as complete
                                },
                                onPendingTask = { task ->
                                    taskViewModel.markTaskPending(task)  // Marks task as pending
                                },
                                onClearCompletedTasks = { // Here is the missing parameter being passed
                                    taskViewModel.clearCompletedTasks()  // Clear completed tasks
                                }
                            )

                            Spacer(modifier = Modifier.weight(1f))

                            Button(onClick = { /* Show the slider */ }, shape = MaterialTheme.shapes.medium) {
                                Text("Accessibility", fontSize = textSize)
                            }

                            Slider(
                                value = textSize.value,
                                onValueChange = { textSize = it.sp },
                                valueRange = 12f..24f,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
