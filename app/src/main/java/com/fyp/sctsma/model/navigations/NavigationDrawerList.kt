package com.fyp.sctsma.model.navigations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector


sealed class NavigationDrawerList(
    var icon : ImageVector,
    var title : String,
    var action: DrawerAction
) {
    object Home : NavigationDrawerList(
        icon = Icons.Filled.Home,
        title = "Home",
        action = DrawerAction.NavigateToHome
    )
    object Profile : NavigationDrawerList(
        icon = Icons.Filled.AccountCircle,
        title = "Profile",
        action = DrawerAction.NavigateToProfile
    )
    object Wallet : NavigationDrawerList(
        icon = Icons.Filled.AccountBalanceWallet,
        title = "Wallet",
        action = DrawerAction.NavigateToWallet
    )
    object Complaints : NavigationDrawerList(
        icon = Icons.Filled.Assessment,
        title = "Complaints",
        action = DrawerAction.NavigateToComplaints
    )
    object Settings : NavigationDrawerList(
        icon = Icons.Filled.Settings,
        title = "Settings",
        action = DrawerAction.NavigateToSettings
    )
    object Logout : NavigationDrawerList(
        icon = Icons.AutoMirrored.Filled.Logout,
        title = "Logout",
        action = DrawerAction.Logout

    )
}