package com.fyp.sctsma.view.composables.navigation

sealed class ExternalRoutes(
    val route: String
) {
    object LandingScreen : ExternalRoutes("landing_screen")
    object LoginScreen : ExternalRoutes("login_screen")
    object RegisterScreen : ExternalRoutes("register_screen")
    object OTPScreen : ExternalRoutes("otp_screen")
    object HomeRoute: ExternalRoutes("home_route")
}