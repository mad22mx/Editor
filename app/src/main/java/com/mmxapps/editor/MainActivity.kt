package com.mmxapps.editor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
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
    val initialText = editorText
    val onTextChange: (String) -> Unit = { editorText = it }
    var text by remember { mutableStateOf(initialText) }
    //val lineCount = text.count { it == '\n' }
    //val lineNoList = (0..lineCount).toList()
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
        val lines = text.split('\n').toMutableList()
        if (lines[lines.size-1].isEmpty() || lines[lines.size-1] == " ") lines[lines.size - 1] = " "
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            items(lines.size) { lineNo ->

                Row(modifier = Modifier.fillMaxSize()) {

                    Box(
                        modifier = Modifier
                            .width(30.dp)
                            .padding(2.dp),
                        contentAlignment = Alignment.TopEnd

                    ) {
                        Text(
                            text = (lineNo+1).toString(),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                            fontFamily = FontFamily.Monospace
                        )
                    }

                    BasicTextField(
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 4.dp),
                        value = lines[lineNo],
                        onValueChange = {
                            text = it
                            onTextChange(text)
                        },
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        ),
                        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                        decorationBox = { innerTextField ->
                            Box(
                                modifier = Modifier
                            ) {
                                if(text.isEmpty()) {
                                    Text(
                                        "Start typing...",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                                    )
                                }
                            }
                            innerTextField()
                        }


                    )

                }
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

