package com.fyp.sctsma.view.composables.commonComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fyp.sctsma.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopNavBar(
    modifier: Modifier,
    title: String,
    scope: CoroutineScope,
    drawerState: DrawerState
) {
    Box(
        modifier = Modifier
            .padding(top = 24.dp)
            .height(60.dp)



    ){
      //  DarkOverlayComponentBar()
//        Spacer(modifier = Modifier.height(15.dp))
        Row(

            modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    Color(0xFFFAFAFA)
                )
            ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier
                    .width(75.dp)
                    .align(Alignment.CenterVertically),
                onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }) {
                Image(
                    painter = painterResource(R.drawable.menu_button),
                    colorFilter = ColorFilter.tint(Color(0xFF2F4858)),
                    contentDescription = "Hamburger",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 0.dp)
                )
            }

            Text(
                text = title ,
                fontSize = 20.sp,
                color = Color(0xFF2F4858),
                fontWeight = FontWeight(500),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 16.dp)

            )
            Image(
                painter = painterResource(R.drawable.user_avatar),
                colorFilter = ColorFilter.tint(Color(0xFF2F4858)),
                contentDescription = "userAvatar",
                modifier = Modifier
                    .width(65.dp)
                    .height(65.dp)
                    .padding(end = 16.dp),

                )
        }
    }


}

@Preview
@Composable
fun TopNavBarPreview() {
    val state = rememberDrawerState(initialValue = DrawerValue.Closed)
    TopNavBar(
        modifier = Modifier,
        title = "Home",
        scope = rememberCoroutineScope(),
        drawerState = state
    )
}


