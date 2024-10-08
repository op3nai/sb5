package com.example.rokuremote.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rokuremote.viewmodel.RokuViewModel

@Composable
fun CustomButtonDialog(
    viewModel: RokuViewModel,
    onDismiss: () -> Unit
) {
    var buttonName by remember { mutableStateOf("") }
    var buttonCommands by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Create Custom Button") },
        text = {
            Column {
                TextField(
                    value = buttonName,
                    onValueChange = { buttonName = it },
                    label = { Text("Button Name") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = buttonCommands,
                    onValueChange = { buttonCommands = it },
                    label = { Text("Commands (comma-separated)") }
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                viewModel.addCustomButton(buttonName, buttonCommands.split(",").map { it.trim() })
                onDismiss()
            }) {
                Text("Create")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}