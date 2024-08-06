//package com.fyp.sctsma.view.uiexperiments
//
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.fyp.sctsma.model.navigations.Destinations
//
//@Composable
//fun NavGraph(modifier: Modifier) {
//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = Destinations.ScreenA.route) {
//        composable(route = Destinations.ScreenA.route) { ScreenA(navController = navController) }
//        composable(route = Destinations.ScreenB.route) { ScreenB(navController = navController) }
//        composable(route = Destinations.ScreenC.route) { ScreenC(navController = navController) }
//    }
//}