package com.fyp.sctsma.view.composables

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fyp.sctsma.R
import com.fyp.sctsma.model.navigations.NavigationDrawerList
import com.fyp.sctsma.view.composables.commonComponents.BottomNavBar
import com.fyp.sctsma.view.composables.commonComponents.TopNavBar
import com.fyp.sctsma.view.composables.navigation.ExternalRoutes
import com.fyp.sctsma.view.composables.navigation.InternalRoutes
import com.fyp.sctsma.view.composables.notifications.NotificationsScreen
import com.fyp.sctsma.viewmodel.SharedViewModel
import components.commonComponents.DarkOverlayComponentBar
import kotlinx.coroutines.launch

@Composable
fun HomeRoute(initialNavController: NavHostController) {

    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val context = LocalContext.current
    val sharedViewModel = remember {SharedViewModel(context)}
    val isLoggedIn = sharedViewModel.isLoggedIn.observeAsState()
    var logoutState by remember { mutableStateOf(false)}
    LaunchedEffect(key1 = logoutState) {
        if (logoutState) {
            initialNavController.popBackStack(0, true)
            initialNavController.navigate(ExternalRoutes.LandingScreen.route)
            Toast.makeText(context, "Session terminated successfully", Toast.LENGTH_SHORT).show()
        }
    }
    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {

            ModalDrawerSheet(
                modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(topEnd = 15.dp)
                    )
                    .fillMaxWidth(.8f)
                    .background(
                        color = Color(0xFFFAFAFA)
                    )
                    .border(
                        width = .1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(topEnd = 15.dp)
                    )
            ) {
                //header
                Box(
                    modifier = Modifier
                        .background(
                            color = Color(0xFF2F4858)
                        )
                        .border(
                            width = .1.dp,
                            color = Color.Black
                        )
                        .height(150.dp)
                    , contentAlignment = Alignment.Center) {
                    Image(

                        painter = painterResource(id = R.drawable.african_american_business_woman_with_phone) ,
                        contentDescription = "African American Business Woman With Phone", contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    //creates the dark like look on the image
                    DarkOverlayComponentBar()
                    Text(
                        text = "Home",
                        modifier = Modifier.wrapContentSize(Alignment.Center),
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFFFAFAFA),
                            textAlign = TextAlign.Center
                        )

                    )

                }
                //divider
                HorizontalDivider()
                //body
                NavigationDrawerItem(

                    icon = {
                        Icon(imageVector = NavigationDrawerList.Home.icon, tint = Color(0xFF2F4858), contentDescription = NavigationDrawerList.Home.title )
                    },
                    label = { Text(text = NavigationDrawerList.Home.title, color = Color(0xFF2F4858)) }
                    , selected = false
                    , onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navController.popBackStack(InternalRoutes.HomeScreen.route, true)
                        navController.navigate(InternalRoutes.HomeScreen.route)

                    })
                NavigationDrawerItem(
                    icon = {
                        Icon(imageVector = NavigationDrawerList.Profile.icon, tint = Color(0xFF2F4858), contentDescription = NavigationDrawerList.Profile.title )
                    },
                    label = { Text(text = NavigationDrawerList.Profile.title, color = Color(0xFF2F4858)) }
                    , selected = false
                    , onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navController.navigate(InternalRoutes.ProfileScreen.route)
                    })
//                NavigationDrawerItem(
//                    icon = {
//                        Icon(imageVector = NavigationDrawerList.Wallet.icon, tint = Color(0xFF2F4858), contentDescription = NavigationDrawerList.Wallet.title )
//                    },
//                    label = { Text(text = NavigationDrawerList.Wallet.title, color = Color(0xFF2F4858)) }
//                    , selected = false
//                    , onClick = {
//                        coroutineScope.launch {
//                            drawerState.close()
//                        }
//                        navController.navigate(InternalRoutes.WalletScreen.route)
//                    })
//                NavigationDrawerItem(
//                    icon = {
//                        Icon(imageVector = NavigationDrawerList.Complaints.icon, tint = Color(0xFF2F4858), contentDescription = NavigationDrawerList.Complaints.title )
//                    },
//                    label = { Text(text = NavigationDrawerList.Complaints.title, color = Color(0xFF2F4858)) }
//                    , selected = false
//                    , onClick = {
//                        coroutineScope.launch {
//                            drawerState.close()
//                        }
//                        navController.navigate(InternalRoutes.ComplaintsScreen.route)
//                    })
//                NavigationDrawerItem(
//                    icon = {
//                        Icon(imageVector = NavigationDrawerList.Settings.icon, tint = Color(0xFF2F4858), contentDescription = NavigationDrawerList.Settings.title )
//                    },
//                    label = { Text(text = NavigationDrawerList.Settings.title, color = Color(0xFF2F4858)) }
//                    , selected = false
//                    , onClick = {
//                        coroutineScope.launch {
//                            drawerState.close()
//                        }
//                        navController.navigate(InternalRoutes.SettingsScreen.route)
//                    })
                NavigationDrawerItem(
                    icon = {
                        Icon(imageVector = NavigationDrawerList.Logout.icon, tint = Color(0xFF2F4858), contentDescription = NavigationDrawerList.Logout.title )
                    },
                    label = { Text(text = NavigationDrawerList.Logout.title, color = Color(0xFF2F4858)) }
                    , selected = false
                    , onClick = {   coroutineScope.launch {
                        drawerState.close()
                    }
                        sharedViewModel.logout()
                        logoutState = true

                    })

            }

        }) {
        Scaffold(
            topBar ={
                TopNavBar(
                    modifier = Modifier,
                    title = stringResource(id = R.string.app_name),
                    scope =  coroutineScope,
                    drawerState = drawerState
                    )
            }
            ,
            bottomBar = {
                BottomAppBar(
                    modifier = Modifier,
                    containerColor = Color(0xFFFAFAFA),
                    contentColor = Color(0xFF2F4858)
                ){
                    BottomNavBar(
                        modifier = Modifier,
                        homeItemClicked = {
                            navController.navigate(InternalRoutes.HomeScreen.route) {
                                popUpTo(InternalRoutes.HomeScreen.route) { inclusive = true }
                            } },
                        orderItemClicked = {
                            navController.navigate(InternalRoutes.OrdersScreen.route) {
                                popUpTo(InternalRoutes.OrdersScreen.route) { inclusive = true }}
                        },
                        notificationItemClicked = {
                            navController.navigate(InternalRoutes.NotificationScreen.route) {
                                popUpTo(InternalRoutes.NotificationScreen.route) {
                                    inclusive = true
                                }}
                        },
                        historyItemClicked = {
                            navController.navigate(InternalRoutes.HistoryScreen.route) {
                                popUpTo(InternalRoutes.HistoryScreen.route) { inclusive = true }}
                        })
                }

            }
        ){ it ->

            NavHost(
                modifier = Modifier.padding(it),
                navController = navController,
                startDestination = InternalRoutes.HomeScreen.route
            ) {
                composable(InternalRoutes.HomeScreen.route) { HomeScreen(navController) }
                composable(InternalRoutes.ProfileScreen.route) { ProfileScreen(navController)}
                composable(InternalRoutes.WalletScreen.route) { }
                composable(InternalRoutes.SettingsScreen.route) { }
                composable(InternalRoutes.ComplaintsScreen.route) { }
                composable(InternalRoutes.OrdersScreen.route) {OrdersScreen(navController) }
                composable(InternalRoutes.NotificationScreen.route) { NotificationsScreen(
                    navController
                )}
                composable(InternalRoutes.HistoryScreen.route) { HistoryScreen(navController) }
                composable(
                    route = InternalRoutes.DetailsScreen.route + "/{id}",
                    arguments = listOf( navArgument("id") { type = NavType.StringType }
                    )) {
                    //reading value stored in backstackEntry
                    backStackEntry ->
                    DetailsScreen(navController, backStackEntry.arguments?.getString("id"))
                }
                composable(InternalRoutes.OrderCreationScreen.route) { CreateOrdersScreen(navController) }
                composable(InternalRoutes.OrderCompletionScreen.route) {
                    CompleteOrdersScreen(navController =navController)
                }

            }

    }

}}

@Composable
fun BottomBarItem(icon: ImageVector, label: String, isSelected: Boolean, onClick: () -> Unit) {
    Column(

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = icon,
                tint = if (isSelected) Color(0xFF2F4858) else Color.Gray, // Change color based on selection
                contentDescription = label,
                modifier = Modifier.size(26.dp)
            )
        }
        Text(text = label, fontSize = 10.sp)
    }
}
@Preview
@Composable
fun HomeRoutePreview() {
    HomeRoute(rememberNavController())
}

