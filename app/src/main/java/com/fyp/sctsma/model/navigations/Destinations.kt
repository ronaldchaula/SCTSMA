package com.fyp.sctsma.model.navigations

sealed class Destinations(
    val route:String
) {
    data object Home: Destinations(route = "home")
    data object Orders: Destinations(route = "orders")
    data object Notifications: Destinations(route = "notifications")
    data object History: Destinations(route = "history")
    data object Profile: Destinations(route = "profile")
}