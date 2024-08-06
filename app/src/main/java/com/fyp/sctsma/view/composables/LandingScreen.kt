package com.fyp.sctsma.view.composables

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fyp.sctsma.R
import com.fyp.sctsma.view.composables.navigation.ExternalRoutes
import com.fyp.sctsma.viewmodel.SharedViewModel


@Composable
fun LandingScreen(navController: NavController) {

    val context = LocalContext.current
    val sharedViewModel = remember {SharedViewModel(context)}
    val isLoggedIn = sharedViewModel.isLoggedIn.observeAsState()
    LaunchedEffect(key1 = isLoggedIn) {
        if (isLoggedIn.value == true) {
            navController.popBackStack(0, true)
            navController.navigate(ExternalRoutes.HomeRoute.route)
            Toast.makeText(context, "Welcome Back", Toast.LENGTH_SHORT).show()
        }
        else{
            navController.popBackStack(0, true)
            navController.navigate(ExternalRoutes.LoginScreen.route)
            Toast.makeText(context, "Please Login", Toast.LENGTH_SHORT).show()
        }
    }
    Landing()
    }

@Composable
fun Landing(
    //pass the navigator component to be used inside the app
) {
   Box(
       modifier = Modifier
           .fillMaxSize()
           .background(color = Color(0xFFFAFAFA))
   ){

Image(
 painter = painterResource(R.drawable.appbackground)
   ,
    contentDescription = "App background",
    modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop
)
       Column(
           modifier = Modifier
               .fillMaxWidth(.85f)
               .fillMaxHeight(.9f)
               .align(Alignment.Center),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.SpaceEvenly
       )
       {
            Text(text = "SCTSMA",
                modifier = Modifier
                    .width(294.dp)
                    .fillMaxHeight(.2f)
                    .align(Alignment.CenterHorizontally),
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFFFAFAFA),
                    textAlign = TextAlign.Center
                )
                )

           Text(text = "Welcome To Social Commerce Trust And Security Management Application",
               modifier = Modifier
                   .width(294.dp)
                   .fillMaxHeight(.5f)
                   .align(Alignment.CenterHorizontally),
               style = TextStyle(
                   fontSize = 28.sp,
                   fontWeight = FontWeight(600),
                   color = Color(0xFF2F4858)
                   )
           )

           Column(
               modifier = Modifier
                   .fillMaxWidth(.8f)
                   .height(191.dp)
                   ,
               horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.SpaceEvenly
           ){
//               Button(
//                   colors = ButtonDefaults.buttonColors(
//                       Color(
//                           0xFF2F4858
//                       )
//                   ),
//                   shape = RoundedCornerShape(15.dp),
//                   modifier = Modifier
//                       .width(191.dp)
//                       .height(47.dp)
//                   ,
//                   onClick = {
//                       //write the navigation code for movement here
//                       navigator.navigate(ExternalRoutes.RegisterScreen.route)
//                   }
//               )
//               {
//                   Text(
//                       modifier = Modifier
//                           .width(112.dp)
//                           .height(23.dp)
//                           .padding(vertical = 0.dp)
//                       ,
//                       text = "Register",
//                       style = TextStyle(
//                           fontSize = 16.sp,
//                           fontWeight = FontWeight(500),
//                           color = Color(0xFF6FC07A),
//                           textAlign = TextAlign.Center
//                       ),
//                   )
//               }

//               Button(
//                   colors = ButtonDefaults.buttonColors(
//                       Color(
//                           0x002F4858
//                       )
//                   ),
//                   border = BorderStroke(width = 1.dp, color = Color(0xFF2F4858)),
//                   shape = RoundedCornerShape(15.dp),
//
//                   modifier = Modifier
//                       .width(191.dp)
//                       .height(47.dp)
//                       .padding(vertical = 0.dp)
//                   ,
//                   onClick = {
//                       navigator.navigate(ExternalRoutes.LoginScreen.route)
//                   }
//               )
//               {
//                   Text(
//                       modifier = Modifier
//                           .width(112.dp)
//                           .height(23.dp)
//                       ,
//                       text = "Login",
//                       style = TextStyle(
//
//                           fontSize = 16.sp,
//                           fontWeight = FontWeight(500),
//                           color = Color(0xFF2F4858),
//                           textAlign = TextAlign.Center
//                       ),
//                   )
//               }
           }

       }
   }
}



@Preview
@Composable
fun LandingPreview(){
    LandingScreen(rememberNavController())
}