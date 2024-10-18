package com.mmxapps.editor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.mmxapps.editor.ui.theme.EditorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EditorTheme {
                Scaffold(
                    
                ) { innerPadding ->
                    Text(
                        text = "Hello Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

