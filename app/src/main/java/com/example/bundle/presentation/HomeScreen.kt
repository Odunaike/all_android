package com.example.bundle.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(onClick: () -> Unit){
    Scaffold {
        Column (
            modifier = Modifier.padding(it)
        ) {
            Text(
                text = "Home Screen"
            )
            Button(
               onClick = onClick
            ) {
                Text(
                    text = "Go to Detail"
                )
            }
        }
    }
}