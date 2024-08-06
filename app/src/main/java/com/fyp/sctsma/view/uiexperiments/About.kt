package com.fyp.sctsma.view.uiexperiments

import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.navigation.NavHostController


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ScreenB(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(text = "Screen B")
        HorizontalDivider()
        Button(onClick = { navController.navigate("screenC") }) {
            Text(text = "Go to Screen C")
        }
    }
}

