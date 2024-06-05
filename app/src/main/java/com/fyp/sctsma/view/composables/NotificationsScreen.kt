package com.fyp.sctsma.view.composables//
//import androidx.compose.foundation.border
//import androidx.compose.material3.Text
//import androidx.compose.material3.Icon
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import tz.co.sctsma.android.navigation.History
//import tz.co.sctsma.android.navigation.Home
//import tz.co.sctsma.android.navigation.Notifications
//import tz.co.sctsma.android.navigation.Orders
//
//
//
//
//
//@Composable
//fun NotificationsScreenBottomNavigation() {
//    val destinationList = listOf(
//        Home,
//        Orders,
//        Notifications,
//        History
//    )
//    val selectedIndex = rememberSaveable {
//        mutableStateOf(0)
//    }
//    BottomNavigation(
//        backgroundColor = Color(0xFFFAFAFA),
//        modifier = Modifier.
//        border(width = 1.dp, Color(0xFF2F4858))
//
//    ) {
//
//        destinationList.forEachIndexed { index, destination ->
//            BottomNavigationItem(
//                label = {
//                    Text(
//                        text = destination.title,
//                        color = Color(0xFF2F4858),
//                        fontSize = 8.sp
//                    )
//                },
//                icon = {
//                    Icon(
//                        painter = painterResource(id = destination.icon),
//                        contentDescription = destination.title,
//                        tint = Color(0xFF2F4858)
//                    )
//                },
//                selected = index == selectedIndex.value,
//                onClick = {
//                    selectedIndex.value = index
//                    navController.navigate(destinationList[index].route) {
//                        popUpTo(Home.route)
//                        launchSingleTop = true
//                    }
//                })
//        }
//
//    }
//
//}
//
//@Composable
//fun NotificationsScreen(){
//
//}