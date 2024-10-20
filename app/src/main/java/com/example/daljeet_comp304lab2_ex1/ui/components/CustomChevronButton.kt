package com.example.daljeet_comp304lab2_ex1.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomChevronButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.LightGray  // Light gray background
        ),
        shape = CutCornerShape(50),  // Chevron-like shape with cut corners
        border = BorderStroke(2.dp, Color.Black),  // Black border for the button
        modifier = Modifier.size(80.dp, 30.dp)  // Adjust button size to be smaller
    ) {
        Text(
            text = text,
            color = Color.Black  // Black text color
        )
    }
}
