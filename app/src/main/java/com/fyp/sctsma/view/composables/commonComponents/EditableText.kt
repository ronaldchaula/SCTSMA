package com.fyp.sctsma.view.composables.commonComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun EditableText() {
    var isEditing by remember { mutableStateOf(false) }
    var textValue by remember { mutableStateOf("Initial Text") }

    Column(modifier = Modifier.padding(16.dp)) {
        if (isEditing) {
            TextField(
                value = textValue,
                onValueChange = { textValue = it },
                label = { Text("Edit Text") }
            )
        } else {
            Text(text = textValue)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            if (isEditing) {
                // Perform save operation here...
                isEditing = false
            } else {
                isEditing = true
            }
        }) {
            Text(if (isEditing) "Save" else "Edit")
        }
    }
}