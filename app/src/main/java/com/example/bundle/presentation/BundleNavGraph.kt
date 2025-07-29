package com.example.bundle.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun BundleNavGraph(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home"
    ){
        composable("home") {
            HomeScreen(
                onClick = {
                    navController.navigate("detail")
                }
            )
        }
        composable("detail") {
            DetailScreen()
        }
    }
}