package com.fyp.sctsma.view.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions


object OrdersTab
    :Tab {
    @Composable
    override fun Content() {
//        Navigator(screen = HomeScreen())
    }


    override val options: TabOptions
        @Composable
        get(){
            val title = "Orders"
            val icon = rememberVectorPainter(Icons.Default.ShoppingCart)
            return TabOptions(
                index = 1u,
                title = title,
                icon = icon
            )
        }

}