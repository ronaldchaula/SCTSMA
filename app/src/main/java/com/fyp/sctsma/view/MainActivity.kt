package com.fyp.sctsma.view

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fyp.sctsma.ui.theme.SCTSMATheme
import com.fyp.sctsma.view.composables.HomeRoute
import com.fyp.sctsma.view.composables.LandingScreen
import com.fyp.sctsma.view.composables.LoginScreen
import com.fyp.sctsma.view.composables.OTPScreen
import com.fyp.sctsma.view.composables.RegistrationScreen
import com.fyp.sctsma.view.composables.navigation.ExternalRoutes
import com.fyp.sctsma.viewmodel.SharedViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            SCTSMATheme {
                val context = LocalContext.current
                val navController = rememberNavController()
                val sharedViewModel = remember { SharedViewModel(context) }
                val isLoggedIn by sharedViewModel.isLoggedIn.observeAsState(false)
                NavigationContainer(navController,isLoggedIn, context)
            }
        }
    }
}

@Composable
fun NavigationContainer(
    initialNavController: NavHostController,
    isLoggedIn: Boolean,
    context: Context
) {
    NavHost(navController = initialNavController, startDestination = ExternalRoutes.LandingScreen.route){
        val optPath = ExternalRoutes.OTPScreen.route + "/{otp}"
        composable(ExternalRoutes.LandingScreen.route){ LandingScreen(initialNavController) }
        composable(ExternalRoutes.LoginScreen.route){ LoginScreen(initialNavController) }
        composable(ExternalRoutes.RegisterScreen.route){ RegistrationScreen(initialNavController) }
        composable(ExternalRoutes.HomeRoute.route){ HomeRoute(initialNavController) }
        composable(
            route = optPath,
            arguments = listOf( navArgument("otp") { type = NavType.StringType }
            )
            ){backStackEntry -> OTPScreen(
            otp = backStackEntry.arguments?.getString("otp") ?: "",
            initialNavController)

        }


    }
}

@Preview(showBackground = true)
@Composable
fun NavigationContainerPreview() {
NavigationContainer( rememberNavController(), false, LocalContext.current)
}
