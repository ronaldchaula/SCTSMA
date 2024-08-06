package com.fyp.sctsma.view.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fyp.sctsma.R
import com.fyp.sctsma.view.composables.navigation.InternalRoutes
import com.fyp.sctsma.viewmodel.SharedViewModel
import components.commonComponents.DarkOverlayComponentBar


@Composable
fun HomeScreen(navController: NavHostController) {

val context = LocalContext.current
val sharedViewModel = remember { SharedViewModel(context) }
val username by sharedViewModel.username.observeAsState()
val lockNumber by sharedViewModel.lipaNumber.observeAsState()



        Home(
            username,
            lockNumber,
            navController
        )
    }




@Composable
fun Home(
    username: String?,
    lockNumber: String?,
    navController: NavHostController
) {

    Box(modifier = Modifier.fillMaxSize() ){
        //main container for the application content
      BackgroundImage()

//header container
Column(
    modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(.936f)
       ,
        horizontalAlignment = Alignment.CenterHorizontally
) {
    Header(
        username = username,
        lockNumber = lockNumber
    )
    Spacer(modifier = Modifier.height(30.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth(.72f)
            .height(120.dp)
            ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
           modifier = Modifier
               .width(120.dp)
               .height(120.dp)
               .shadow(
                   elevation = 10.dp,
                   shape = RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp),
                   clip = false
               )
               .background(
                   color = Color(0xFFFAFAFA),
                   shape = RoundedCornerShape(size = 15.dp)
               )
               .border(
                   width = 0.5.dp,
                   color = Color(0xFF6FC07A),
                   shape = RoundedCornerShape(size = 15.dp)
               )
        ) {
Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceEvenly,
    modifier = Modifier.fillMaxSize()
) {
    Text(
        text = "This Month",
        modifier = Modifier.wrapContentSize(Alignment.Center),
        style = TextStyle(
            fontSize = 15.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF2F4858),
            textAlign = TextAlign.Center
        ))
    Text(
        text = "0",
        modifier = Modifier.wrapContentSize(Alignment.Center),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF2F4858),
            textAlign = TextAlign.Center
        ))
    Text(
        text = "Completed",
        modifier = Modifier.wrapContentSize(Alignment.Center),
        style = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF2F4858),
            textAlign = TextAlign.Center
        ))
}
        }
        Box(
            modifier = Modifier
                .width(120.dp)
                .height(120.dp)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp),
                    clip = false
                )
                .background(
                    color = Color(0xFFFAFAFA),
                    shape = RoundedCornerShape(size = 15.dp)
                )
                .border(
                    width = 0.5.dp,
                    color = Color(0xFF6FC07A),
                    shape = RoundedCornerShape(size = 15.dp)
                )


        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxSize()

            ) {
                Text(
                    text = "This Year",
                    modifier = Modifier.wrapContentSize(Alignment.Center),
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF2F4858),
                        textAlign = TextAlign.Center
                    ))
                Text(
                    text = "0",
                    modifier = Modifier.wrapContentSize(Alignment.Center),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF2F4858),
                        textAlign = TextAlign.Center
                    ))
                Text(
                    text = "Completed",
                    modifier = Modifier.wrapContentSize(Alignment.Center),
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF2F4858),
                        textAlign = TextAlign.Center
                    ))
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth(.72f)
            .height(120.dp),
horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        if (username != null) {
            Button(
                onClick = {
                    navController.navigate(InternalRoutes.OrderCreationScreen.route)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2F4858)
                ),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                enabled = username.isNotEmpty()

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        painter = (painterResource(R.drawable.create_ic)),
                        contentDescription ="",
                        modifier = Modifier
                            .width(34.dp)
                            .height(34.dp))
                    Spacer(modifier = Modifier.width(40.dp)) // Space between icon and text
                    Text(
                        text = "Create Order",
                        modifier = Modifier.wrapContentSize(Alignment.Center),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFAFAFA),
                            textAlign = TextAlign.Center,
                        )
                    )
                    Spacer(modifier = Modifier.width(45.dp))
                }

            }
        }

        Button(
            onClick = {
                navController.navigate(InternalRoutes.OrderCompletionScreen.route)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2F4858)
            ),
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)

        ) {
            Image(
                painter = (painterResource(R.drawable.qr_code_ic)),
                contentDescription ="",
                modifier = Modifier
                    .width(26.dp)
                    .height(26.dp))
            Spacer(modifier = Modifier.width(38.dp)) // Space between icon and text
            Text(
                text = "Complete Orders",
                modifier = Modifier.wrapContentSize(Alignment.Center),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFAFAFA),
                    textAlign = TextAlign.Center,
                )
            )
            Spacer(modifier = Modifier.width(30.dp))
        }
    }
}

//        @OptIn(ExperimentalVoyagerApi::class)


    }

}












@Composable
fun Header(
    username: String?,
    lockNumber: String?
){
    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(.42f)
            .border(width = 1.dp, color = Color(0xFF000000))
    ){
        //top cover image
        HomeScreenBackgroundImage()
        //dark color on top of the image
        DarkOverlayComponentBar()
        //top navbar
        ContainedHeaderContent(
            username = username,
            lockNumber = lockNumber
        )



        //divider between the top part and the stats container

    }
}

@Composable
private fun ContainedHeaderContent(
    username: String? = null,
    lockNumber: String? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier = Modifier.fillMaxHeight(.30f))
        HomeGreeting(
            username = username,
            lockNumber = lockNumber
        )
    }
}

@Composable
private fun HomeGreeting(
    username: String? = null,
    lockNumber: String? = null
) {
    Text(
        text = "Welcome back $username\n\n Lipa #: $lockNumber",
        fontSize = 18.sp,
        color = Color(0xFFFAFAFA),
        fontWeight = FontWeight(500),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(top = 20.dp)

    )
}

@Composable
private fun HomeScreenBackgroundImage() {
    Image(
        painter = painterResource(R.drawable.african_american_business_woman_with_phone),
        contentDescription = "African american stock",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()


    )
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navController = rememberNavController()
    )
}

