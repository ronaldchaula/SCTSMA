package com.fyp.sctsma.view.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator

class HomeRoute:Screen{
    @Composable
    override fun Content() {
        HomeRouteContainer()
    }
}
@Composable
@Preview
fun HomeRouteContainer() {
    MaterialTheme {
//            Navigator(screen = LandingScreen()){
//               navigator -> SlideTransition(navigator)
//            }
        TabNavigator(HomeTab) {
            Scaffold(
                content = { paddingValues ->
                    Box(
                        modifier = Modifier
                            .padding(bottom = paddingValues.calculateBottomPadding())
                    ) {
                        CurrentTab()
                    }
                },
                bottomBar = {
                    NavigationBar {
                        TabNavigationItem(HomeTab)
                        TabNavigationItem(OrdersTab)
                        TabNavigationItem(NotificationsTab)
                        TabNavigationItem(HistoryTab)
                    }
                }
            )
        }
    }
}




@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    NavigationBarItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = {
            tab.options.icon?.let { painter ->
                Icon(
                    painter = painter,
                    contentDescription = tab.options.title,
                )
            }
        },
        label = {
            Text(text = tab.options.title, style = TextStyle(fontSize = 10.sp))
        }
    )
}