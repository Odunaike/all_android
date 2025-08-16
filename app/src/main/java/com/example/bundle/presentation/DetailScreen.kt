package com.example.bundle.presentation

import android.opengl.Visibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bundle.model.Note
import com.example.bundle.presentation.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    noteID: Int,
    navigateBack: () -> Unit,
    viewModel: NoteViewModel
){
    val note  = viewModel.getNote(noteID)

    var title by remember{
        mutableStateOf(note.title)
    }
    var description by remember {
        mutableStateOf(note.description)
    }
    var isEditable by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }},
                actions = {
                    IconButton(onClick = {
                        isEditable = !isEditable
                    }) {
                        Icon(Icons.Outlined.Edit, contentDescription = null)
                    }
                },
                title = {},
            )
        }
    ) {
        Column (
            modifier = Modifier.padding(it).padding(horizontal = 8.dp)
        ) {
            TextField(
                enabled = isEditable,
                value = title,
                textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.W600),
                shape = RoundedCornerShape(size = 20.dp),
                onValueChange = {
                    title = it
                },
                placeholder = {
                    Text(
                        text = "Title",
                        fontSize = 20.sp,
                    )
                },
                modifier = Modifier.padding(bottom = 10.dp).fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                )
            )
            TextField(
                enabled = isEditable,
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
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
            if (isEditable){
                ElevatedButton(
                    onClick = {
                        viewModel.updateNote(
                            id = noteID,
                            note = Note(title = title, description = description )
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors()
                ) {
                    Text(text = "Save")
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            }

        }
    }
}