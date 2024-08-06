package com.fyp.sctsma.view.uiexperiments

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.fyp.sctsma.model.navigations.BottomMenuList

@Composable

fun BottomNavBar( navController: NavController){
    //predefined objects of the bottom navigation bar
    val bottomNavigationList = listOf(BottomMenuList.Home,BottomMenuList.Orders,BottomMenuList.Notification, BottomMenuList.History)


}

