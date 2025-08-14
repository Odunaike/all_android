package com.example.bundle.presentation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.bundle.model.Note
import kotlinx.serialization.Serializable

@Serializable
object Home
@Serializable
data class Detail(val noteID: Int)
@Serializable
object AddNote

@Composable
fun BundleNavGraph(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Home
    ){
        composable<Home> {
            HomeScreen(
                onItemClick = { noteID ->
                    navController.navigate(route = Detail(noteID = noteID))
                },
                onAddNoteClick = {
                    navController.navigate(route = AddNote)
                }
            )
        }
        composable<Detail>{
            val detail = it.toRoute<Detail>()
            DetailScreen(
                noteID = detail.noteID,
                navigateBack = {navController.popBackStack()}
            )
        }

        composable<AddNote>{
            AddNoteScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}