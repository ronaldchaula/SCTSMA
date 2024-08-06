package com.fyp.sctsma.view.composables

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout


@Composable
fun ConstrainedUI(){
        //how to create a constraint layout
        ConstraintLayout(modifier = Modifier.fillMaxSize() ) {
                val (
               orderButton, 
                ) = createRefs()
                
        }
}

@Composable
fun ConstrainedUIPreview(){
        ConstrainedUI()
}