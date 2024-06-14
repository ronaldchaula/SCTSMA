package com.fyp.sctsma.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.fyp.sctsma.ui.theme.SCTSMATheme
import com.fyp.sctsma.view.composables.LandingScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            SCTSMATheme {
                Scaffold { innerPadding ->
                    NavigationContainer(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun NavigationContainer(
    modifier: Modifier
    ) {
    Navigator(
        screen = LandingScreen()
    ){
            navigator -> SlideTransition(navigator = navigator)
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationContainerPreview() {
NavigationContainer(
    modifier = Modifier.fillMaxSize()
)
}