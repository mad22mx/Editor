package com.mmxapps.editor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mmxapps.editor.ui.theme.EditorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EditorTheme {
                TextEditorScreen()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextEditorScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Simple Text Editor") },
                actions = {
                    IconButton(onClick = { /* New File */ }) {
                        Icon(Icons.Default.Add, contentDescription = "New")
                    }
                    IconButton(onClick = { /* Open File */ }) {
                        Icon(Icons.Default.Delete, contentDescription = "Open")
                    }
                    IconButton(onClick = { /* Save File */ }) {
                        Icon(Icons.Default.Done, contentDescription = "Save")
                    }
                }
            )
        }
    ) { innerPadding ->
        var text by remember { mutableStateOf("") }

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            item {
                TextField(
                    modifier = Modifier.fillMaxSize(),
                    value = text,
                    onValueChange = { text = it },
                    textStyle = TextStyle(fontSize = 16.sp),
                    colors = TextFieldDefaults.colors().copy(
                        unfocusedContainerColor = Color(0xFF262626),
                        focusedContainerColor = Color(0xff303030)
                    ),
                    placeholder = { Text("Start typing...", color = Color.Gray) },
                    maxLines = Int.MAX_VALUE, // Allow unlimited lines
                    singleLine = false, // Multiline
                )
            }

        }
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "51:22",
                modifier = Modifier.padding(8.dp),
                style = TextStyle(color = Color.Gray)
            )
        }
    }

}
