package com.example.bundle.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bundle.model.Note
import com.example.bundle.presentation.viewmodel.NoteViewModel

@Composable
fun AddNoteScreen(
    navigateBack: () -> Unit,
    viewModel: NoteViewModel
){

    var title by remember{
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }

    Scaffold {
        Column (
            modifier = Modifier.padding(it).padding(horizontal = 8.dp).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){

            TextField(
                value = title,
                shape = RoundedCornerShape(size = 20.dp),
                onValueChange = {
                    title = it
                },
                placeholder = {
                    Text(text = "Title")
                },
                modifier = Modifier.padding(bottom = 10.dp).fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            TextField(
                value = description,
                placeholder = {
                    Text(text = "Content")
                },
                shape = RoundedCornerShape(size = 20.dp),
                onValueChange = {
                    description = it
                },
                modifier = Modifier.weight(1f).fillMaxWidth().padding(bottom = 10.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            ElevatedButton(
                onClick = {
                    viewModel.addNote(
                        Note(title = title, description = description)
                    )
                    navigateBack()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors()
            ) {
                Text(text = "Add Note")
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddNoteScreenPreview(){
}
