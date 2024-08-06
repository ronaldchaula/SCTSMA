package com.fyp.sctsma.view.composables.commonComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fyp.sctsma.R

@Composable
fun NavigationPanel(
    controlName: String,
    navController: NavController
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth(.9f)
            .fillMaxHeight(.1f)

    ){
        Image(
            painterResource(
                R.drawable.back_button
            ),
            contentDescription = "Back button"
            , modifier = Modifier
                .height(30.dp)
                .width(45.dp)
                .clickable(
                    onClick = {
                        navController.popBackStack()
                    }
                )
        )
        Text(
            text = controlName,
            fontSize = 20.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF2F4858),
            textAlign = TextAlign.Center

            )
        Spacer(modifier = Modifier
            .width(45.dp))
    }

}

@Preview
@Composable
fun NavigationPanelPreview(){
    NavigationPanel(controlName = "Control Name", navController = rememberNavController())
}