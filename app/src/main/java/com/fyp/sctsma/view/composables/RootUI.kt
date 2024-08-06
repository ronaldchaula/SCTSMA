//package com.fyp.sctsma.view.composables
//
//import android.content.Context
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.wrapContentHeight
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.DrawerValue
//import androidx.compose.material3.Icon
//import androidx.compose.material3.ModalDrawerSheet
//import androidx.compose.material3.ModalNavigationDrawer
//import androidx.compose.material3.NavigationBar
//import androidx.compose.material3.NavigationDrawerItem
//import androidx.compose.material3.NavigationDrawerItemDefaults
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.rememberDrawerState
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import cafe.adriel.voyager.navigator.Navigator
//import cafe.adriel.voyager.navigator.tab.TabNavigator
//import com.fyp.sctsma.model.navigations.NavigationDrawerList
//import com.fyp.sctsma.model.userData.DrawerAction
//import com.fyp.sctsma.view.composables.commonComponents.TopNavBar
//import com.fyp.sctsma.viewmodel.SharedViewModel
//import kotlinx.coroutines.launch
//
//@Composable
//fun RootUI(
//    context: Context,
//    viewModel: SharedViewModel,
//    customComposable: @Composable () -> Unit = {},
//    navigator: Navigator
//){
//
//    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
//
//    //calling objects
//    val scope = rememberCoroutineScope()
//    var items = listOf(
//        NavigationDrawerList.Home,
//        NavigationDrawerList.Profile,
//        NavigationDrawerList.Wallet,
//        NavigationDrawerList.Complaints,
//        NavigationDrawerList.Settings,
//        NavigationDrawerList.Logout
//    )
//    //handle drawer action
//    fun handleDrawerAction(action: DrawerAction, navigator: Navigator, sharedViewModel: SharedViewModel) {
//        when (action) {
//            DrawerAction.NavigateToHome -> navigator.push(HomeRoute(Modifier))
//            DrawerAction.NavigateToProfile -> navigator.push(ProfileScreen())
//            DrawerAction.NavigateToWallet -> navigator.push(WalletScreen())
//            DrawerAction.NavigateToComplaints -> navigator.push(ComplaintScreen())
//            DrawerAction.NavigateToSettings -> navigator.push(SettingsScreen())
//            DrawerAction.Logout -> {
//                sharedViewModel.logout()
//                navigator.popUntilRoot()
//                navigator.push(LoginScreen())
//            }
//        }
//    }
//    var selectedItem by remember { mutableStateOf(items[0]) }
//
//
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
//                        color = Color(
//                            0xFF2F4858
//                        ),
//                        shape = RoundedCornerShape(topEnd = 15.dp, bottomEnd = 15.dp)
//                    )
//            ) {
//
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .fillMaxHeight()
//                        .padding(10.dp)
//                ){
//
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .fillMaxHeight(.3f)
//                            .background(Color.White)
//                            .padding(10.dp)
//                    ){
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
//
//                            )
//                        }
//                    }
//                    Spacer(modifier = Modifier.fillMaxHeight(.1f))
//                    //here is where we are going to put a list of content
//                    items.forEach {
//                            item ->
//                        NavigationDrawerItem(
//                            icon = {
//                                //icon is the image vector
//                                Icon(item.icon, contentDescription = item.title)
//                            },
//                            label = {
//                                //label of an item
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
//                                selectedItem = item
//                                handleDrawerAction(item.action, navigator, viewModel)
//                            },
//                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
//                        )
//                    }
//                }
//
//            }
//
//        }    ) {
//
//            Scaffold(
//                //custom app bar
//                topBar = {
//                    TopNavBar(
//                        modifier = Modifier.padding(top = 0.dp),
//                        title = "Home",
//                        state = state,
//
//                        )
//                },
//                //content of the page goes here
//                content = { paddingValues ->
//                    Box(
//                        modifier = Modifier
//                            .padding(bottom = paddingValues.calculateBottomPadding())
//                    ) {
//                        customComposable
//                    }
//                },
//                //bottom tabbed navigation bar
//                bottomBar = {
//                    NavigationBar {
//                        TabNavigationItem(HomeTab)
//                        TabNavigationItem(OrdersTab)
//                        TabNavigationItem(NotificationsTab)
//                        TabNavigationItem(HistoryTab)
//                    }
//                }
//            )
//
//
//    }
//
//
//
//}