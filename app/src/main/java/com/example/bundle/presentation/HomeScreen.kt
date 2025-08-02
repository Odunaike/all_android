package com.example.bundle.presentation

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bundle.model.Note

val notes = listOf(
    Note(title = "Grind", description = "This is the description"),
    Note(title = "Workout", description = "This is the description"),)


@Composable
fun HomeScreen( onItemClick: (Int)-> Unit){

    Scaffold {
        LazyColumn(
            modifier = Modifier.padding(it)
        ){
            items(items = notes) {
                ItemCard(
                    modifier = Modifier.height(100.dp),
                    onItemClick = onItemClick,
                    note = it,
                    id = notes.indexOf(it)
                )
            }
        }
    }
}

@Composable
fun ItemCard(modifier: Modifier = Modifier, onItemClick: (Int) -> Unit, note: Note, id: Int){
    Surface(
        modifier = modifier.padding(10.dp).fillMaxSize()
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
            modifier = Modifier.padding(8.dp).fillMaxHeight(),
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