package com.fyp.sctsma.model.navigations

sealed class BottomBarAction {
    object NavigateToHome : BottomBarAction()
    object NavigateToOrders : BottomBarAction()
    object NavigateToNotifications : BottomBarAction()
    object NavigateToHistory : BottomBarAction()

}