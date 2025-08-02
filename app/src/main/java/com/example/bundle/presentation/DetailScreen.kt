package com.example.bundle.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bundle.model.Note

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    noteID: Int
){
    val note  = notes.get(noteID)
    Scaffold {
        Column (
            modifier = Modifier.padding(it)
        ) {
            Text(
                text = "Detail Screen"
            )
            Text(
                text = "Title: ${note.title}"
            )
        }
    }
}