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
                }
            )
        }
        composable<Detail>{
            val detail = it.toRoute<Detail>()
            DetailScreen(noteID = detail.noteID)
        }
    }
}