package com.example.daljeet_comp304lab2_ex1.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ClearCompletedTasksButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.LightGray
        ),
        shape = MaterialTheme.shapes.small,  // Standard button shape
        border = BorderStroke(1.dp, Color.Black),  // Add border
        modifier = Modifier.size(120.dp, 40.dp)  // Adjust size as needed
    ) {
        Text(
            text = "Clear Completed",
            color = Color.Black
        )
    }
}
