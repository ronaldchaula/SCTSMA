//package com.fyp.sctsma.view.composables
//
//import android.content.Context
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import cafe.adriel.voyager.core.screen.Screen
//import cafe.adriel.voyager.navigator.Navigator
//import com.fyp.sctsma.model.navigations.DrawerAction
//import com.fyp.sctsma.model.navigations.NavigationDrawerList
//import com.fyp.sctsma.viewmodel.SharedViewModel
//import kotlinx.coroutines.launch
//
//class NavigationDrawerContent: Screen {
//
//}
//
//
//
//@Composable
//fun NavigationDrawerContent(
//    context: Context,
//    viewModel: SharedViewModel,
//    navigator: Navigator,
//    onItemSelected: (DrawerAction) -> Unit
//) {
//    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
//    val scope = rememberCoroutineScope()
//
//    val items = listOf(
//        NavigationDrawerList.Home,
//        NavigationDrawerList.Profile,
//        NavigationDrawerList.Wallet,
//        NavigationDrawerList.Complaints,
//        NavigationDrawerList.Settings,
//        NavigationDrawerList.Logout
//    )
//
//    var selectedItem by remember { mutableStateOf(items[0]) }
//
//    ModalNavigationDrawer(
//        drawerState = drawerState,
//        gesturesEnabled = true,
//        drawerContent = {
//            ModalDrawerSheet(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .fillMaxHeight()
//                    .clip(RoundedCornerShape(topEnd = 15.dp, bottomEnd = 15.dp))
//                    .background(Color(0xFFFAFAFA))
//                    .border(
//                        width = .5.dp,
//                        color = Color(0xFF2F4858),
//                        shape = RoundedCornerShape(topEnd = 15.dp, bottomEnd = 15.dp)
//                    )
//            ) {
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .fillMaxHeight()
//                        .padding(10.dp)
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .fillMaxHeight(.3f)
//                            .background(Color.White)
//                            .padding(10.dp)
//                    ) {
//                        BackgroundImage()
//                        Column(
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .padding(10.dp)
//                        ) {
//                            Text(
//                                text = "Side Menu",
//                                style = TextStyle(
//                                    fontSize = 28.sp,
//                                    fontWeight = FontWeight(500),
//                                    color = Color(0xFF2F4858),
//                                    textAlign = TextAlign.Center
//                                ),
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .wrapContentHeight()
//                            )
//                        }
//                    }
//                    Spacer(modifier = Modifier.fillMaxHeight(.1f))
//                    items.forEach { item ->
//                        NavigationDrawerItem(
//                            icon = {
//                                Icon(item.icon, contentDescription = item.title)
//                            },
//                            label = {
//                                Text(item.title)
//                            },
//                            selected = item == selectedItem,
//                            onClick = {
//                                scope.launch {
//                                    if (drawerState.isOpen) {
//                                        drawerState.close()
//                                    }
//                                }
//                                selectedItem = item
//                                onItemSelected(item.action)
//                            },
//                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
//                        )
//                    }
//                }
//            }
//        }
//    )
//}