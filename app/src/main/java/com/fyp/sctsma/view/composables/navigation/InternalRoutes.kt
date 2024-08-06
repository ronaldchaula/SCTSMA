package com.fyp.sctsma.view.composables.navigation

sealed class InternalRoutes(
    val route: String
) {
    object HomeScreen : InternalRoutes("home_screen")
    object OrderCreationScreen : InternalRoutes("order_creation_screen")
    object OrdersScreen : InternalRoutes("orders_screen")
    object DetailsScreen : InternalRoutes("details_screen")
    object PaymentRequestScreen : InternalRoutes("payment_request_screen")
    object NotificationScreen : InternalRoutes("notification_screen")
    object HistoryScreen : InternalRoutes("history_screen")
    object ProfileScreen : InternalRoutes("profile_screen")
    object WalletScreen : InternalRoutes("wallet_screen")
    object ComplaintsScreen : InternalRoutes("complaints_screen")
    object SettingsScreen : InternalRoutes("settings_screen")

    object OrderCompletionScreen : InternalRoutes("qr_scanner_screen")

}