package com.mmxapps.editor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
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
    var editorText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Simple Text Editor") },
                actions = {
                    IconButton(onClick = { editorText = "" }) {
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
        Row(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // Line numbers column
            val lines = editorText.split("\n")
            Box(
                modifier = Modifier
                    .width(30.dp)
                    .fillMaxHeight()
            ) {
                LazyColumn {
                    items(lines.size) { index ->
                        val nooflines = lines[index].length % 38
                        Text(
                            text = (index + 1).toString(),
                            modifier = Modifier
                                .padding(end = 8.dp, top = 4.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                            fontFamily = FontFamily.Monospace
                        )
                        for (i in 1..nooflines) {
                            Text(
                                text = "",
                                modifier = Modifier
                                    .padding(end = 8.dp, top = 4.dp)
                                    .fillMaxWidth(),
                                textAlign = TextAlign.End,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }
                }
            }

            // Main text editor
            BasicTextField(
                value = editorText,
                onValueChange = { newText ->
                    editorText = newText
                },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                textStyle = TextStyle(
                    color = Color(0xFFFFFFFF),
                    fontSize = 16.sp,
                    fontFamily = FontFamily.Monospace,
                    lineHeight = 24.sp,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    )
                ),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .padding(top = 4.dp)
                    ) {
                        if (editorText.isEmpty()) {
                            Text(
                                "Start typing...",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                            )
                        }
                        innerTextField()
                    }
                }
            )
        }

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            val lines = editorText.split("\n")
            Text(
                text = "${lines.size}:${editorText.length}",
                style = TextStyle(color = Color.Gray)
            )
        }
    }
}