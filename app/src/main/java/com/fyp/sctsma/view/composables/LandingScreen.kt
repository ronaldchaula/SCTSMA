package com.fyp.sctsma.view.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.fyp.sctsma.R


class LandingScreen: Screen{
    @Composable
    override fun Content(){
        // we write our content inside this area
        // define the navigator component here
        val navigator = LocalNavigator.currentOrThrow


            Landing(navigator)



    }
}
@Composable
fun Landing(
    //pass the navigator component to be used inside the app
    navigator : Navigator = LocalNavigator.currentOrThrow
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
                   .align(Alignment.Start),
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
               Button(
                   colors = ButtonDefaults.buttonColors(
                       Color(
                           0xFF2F4858
                       )
                   ),
                   shape = RoundedCornerShape(15.dp),
                   modifier = Modifier
                       .width(191.dp)
                       .height(47.dp)
                   ,
                   onClick = {
                       //write the navigation code for movement here
                       navigator.push(RegistrationScreen())
                   }
               )
               {
                   Text(
                       modifier = Modifier
                           .width(112.dp)
                           .height(23.dp)
                           .padding(vertical = 0.dp)
                       ,
                       text = "Register",
                       style = TextStyle(
                           fontSize = 16.sp,
                           fontWeight = FontWeight(500),
                           color = Color(0xFF6FC07A),
                           textAlign = TextAlign.Center
                       ),
                   )
               }

               Button(
                   colors = ButtonDefaults.buttonColors(
                       Color(
                           0x002F4858
                       )
                   ),
                   border = BorderStroke(width = 1.dp, color = Color(0xFF2F4858)),
                   shape = RoundedCornerShape(15.dp),

                   modifier = Modifier
                       .width(191.dp)
                       .height(47.dp)
                       .padding(vertical = 0.dp)
                   ,
                   onClick = {
                       navigator.push(LoginScreen())
                   }
               )
               {
                   Text(
                       modifier = Modifier
                           .width(112.dp)
                           .height(23.dp)
                       ,
                       text = "Login",
                       style = TextStyle(

                           fontSize = 16.sp,
                           fontWeight = FontWeight(500),
                           color = Color(0xFF2F4858),
                           textAlign = TextAlign.Center
                       ),
                   )
               }
           }

       }
   }
}



@Preview()
@Composable
fun LandingPreview(){
    Landing( )
}