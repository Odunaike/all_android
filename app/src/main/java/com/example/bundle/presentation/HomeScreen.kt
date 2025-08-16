package com.example.bundle.presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bundle.model.Note
import com.example.bundle.presentation.viewmodel.NoteViewModel


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen(
    onItemClick: (Int)-> Unit,
    onAddNoteClick: () -> Unit,
    viewModel: NoteViewModel
){

    val _notes = viewModel.getAllNotes()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(text = "Bundle")},
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddNoteClick,
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ){
            items(
                items = _notes,
                key = {
                    it.hashCode()
                }
            ) {
               SwipeToDismissItem(
                   modifier = Modifier.height(100.dp).padding(bottom = 5.dp, start = 5.dp, end = 5.dp).animateItem(),
                   note = it,
                   id = _notes.indexOf(it),
                   onItemClick = onItemClick,
                   onSwipeDeleteItem = {
                       viewModel.removeNote(it)
                   }
               )
            }
        }
    }
}

@Composable
fun SwipeToDismissItem(modifier: Modifier, note: Note, id: Int, onItemClick: (Int) -> Unit, onSwipeDeleteItem: ()-> Unit){

    var swipeToDismissBoxState = rememberSwipeToDismissBoxState (
        confirmValueChange = {
            if (it == SwipeToDismissBoxValue.EndToStart){
                onSwipeDeleteItem()
            }
            it == SwipeToDismissBoxValue.EndToStart
        }
    )

    SwipeToDismissBox(
        modifier = modifier,
        state = swipeToDismissBoxState,
        backgroundContent = {
            when (swipeToDismissBoxState.dismissDirection){
                SwipeToDismissBoxValue.StartToEnd -> {}
                SwipeToDismissBoxValue.EndToStart -> {
                    Icon(
                        Icons.Outlined.Delete,
                        contentDescription = null,
                        modifier = modifier
                            .fillMaxSize()
                            .background(color = Color.Red)
                            .wrapContentSize(Alignment.CenterEnd)
                            .padding(end = 10.dp),
                        tint = Color.White
                    )
                }
                SwipeToDismissBoxValue.Settled -> {}
            }

        }

    ) {
        ItemCard(modifier = modifier, onItemClick = onItemClick, note = note, id = id)
    }
}

@Composable
fun ItemCard(modifier: Modifier, onItemClick: (Int) -> Unit, note: Note, id: Int){
    Surface(
        modifier = modifier
            .height(100.dp)
            .fillMaxSize()
            .clickable(
                enabled = true,
                onClick = {
                    onItemClick(id)
                }
            ),
        shape = RoundedCornerShape(size = 7.dp),
        color = MaterialTheme.colorScheme.surfaceContainer
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = note.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600
                )
                Text(
                    text = note.description,
                    color = Color.DarkGray
                )
            }
            Icon(Icons.Outlined.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.DarkGray)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview(){
}