package com.fyp.sctsma.view.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object HomeTab
    :Tab {
    @Composable
    override fun Content() {
     Navigator(screen = HomeScreen())
    }


    override val options: TabOptions
        @Composable
        get(){
            val title = "Home"
            val icon = rememberVectorPainter(Icons.Default.Home)
            return TabOptions(
                index = 0u,
                title = title,
                icon = icon
            )
        }

}