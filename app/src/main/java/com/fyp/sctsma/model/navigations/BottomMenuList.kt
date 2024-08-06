package com.fyp.sctsma.model.navigations

import androidx.compose.ui.graphics.vector.ImageVector
import com.fyp.sctsma.R

sealed class BottomMenuList(
    val icon: Int,
    val label: String,
    val action: BottomBarAction
) {
    object Home : BottomMenuList(R.drawable.home_ic, "Home",BottomBarAction.NavigateToHome)
    object Orders : BottomMenuList(R.drawable.orders_ic, "Orders", BottomBarAction.NavigateToOrders)
    object Notification : BottomMenuList(R.drawable.notification_ic, "Notifications", BottomBarAction.NavigateToNotifications)
    object History : BottomMenuList(R.drawable.history_ic, "History", BottomBarAction.NavigateToHistory)

}