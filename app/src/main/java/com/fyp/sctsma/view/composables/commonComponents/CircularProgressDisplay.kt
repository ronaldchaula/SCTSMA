package com.fyp.sctsma.view.composables.commonComponents

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgressDisplay(
    isLoading: Boolean = false,
    color: Color = Color.Blue
    ) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)

    ) {
        if (isLoading) {
            androidx.compose.material3.CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(39.dp)
                    .height(39.dp),
                color = Color(0xFFFAFAFA)
            )
        }

    }
}