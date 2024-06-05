package com.fyp.sctsma.view.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.fyp.sctsma.R

class RegistrationScreen : Screen{
    @Composable
    override fun Content() {
        val registrationNavigator = LocalNavigator.currentOrThrow
        Register(registrationNavigator)
    }
}
@Composable
fun Register(navigator : Navigator = LocalNavigator.currentOrThrow) {

    //state management
    val verticalScrollState = rememberScrollState()
    val phoneNumber = remember {mutableStateOf("")}
    val password = remember { mutableStateOf("")    }
    val verifyPassword = remember { mutableStateOf("")}
    val selectedBusiness = remember { mutableIntStateOf(0) }
//central container begins
    BackgroundImage()

    //top layer starts here
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(verticalScrollState, enabled = true),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        )

        {

            //App title text
            Text(text = stringResource(R.string.app_name),
                modifier = Modifier
                    .width(294.dp)
                  ,
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFAFAFA),
                    textAlign = TextAlign.Center
                )

            )

            //Login page heading 2

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(text = stringResource(R.string.preliminary),
                    modifier = Modifier
                        .width(294.dp)
                        .wrapContentSize(Alignment.CenterStart)
                        .padding(
                            start = 8.dp,
                            bottom = 0.dp,
                            top = 0.dp
                        ),

                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF2F4858),
                        textAlign = TextAlign.Start
                    )

                )
                Text(text = stringResource(R.string.registration),
                    modifier = Modifier
                        .width(294.dp)
                        .padding(
                            start = 8.dp,
                            bottom = 5.dp,
                            top = 0.dp
                        ),

                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF2F4858),
                        textAlign = TextAlign.Start
                    )

                )
            }



            //Login form container

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .width(294.dp)
                    .height(480.dp)
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 15.dp,
                            topEnd = 15.dp,
                            bottomStart = 15.dp,
                            bottomEnd = 15.dp
                        )
                    )
                    .background(Color(0xFFFAFAFA))
                    .border(
                        width = 1.dp,
                        Color(0x302F4858),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .shadow( // Add shadow properties here
                        elevation = .1.dp, // Adjust shadow elevation (distance)
                        ambientColor = Color.LightGray, // Color for ambient light
                        spotColor = Color.DarkGray // Color for light source
                    )
                ){

                //Phone number label
                Text(
                    text = "Phone Number",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF2F4858)
                        ),
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .padding(bottom = 5.dp)
                )

                //Phone number input
                OutlinedTextField(
                    value = phoneNumber.value ,
                                        onValueChange ={
                                   phoneNumber.value = it
                    },
                    placeholder = {
                        Text(text = "(+255)07XXXXXXXX",
                            fontSize = 12.sp
                            )
                    },
                    //styling

                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .height(55.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFF2F4858),
                            shape = RoundedCornerShape(15.dp)
                        )
                        .background(Color(0xFFFAFAFA))
                )


                //Password
                Text(
                    text = "Password",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF2F4858)
                    ),
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .padding(bottom = 5.dp, top = 5.dp)
                )

                //Password
                OutlinedTextField(
                    value = password.value ,
                    onValueChange ={
                                   password.value = it
                    },
                    placeholder = {
                        Text(text = "Type password here",
                            fontSize = 12.sp)
                    },
                    //styling

                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .height(55.dp)
                        .border(
                            width = .1.dp,
                            color = Color(0xFF2F4858),
                            shape = RoundedCornerShape(15.dp)
                        )
                        .background(Color(0xFFFAFAFA))
                )

                Text(
                    text = "Verify Password",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF2F4858)
                    ),
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .padding(bottom = 5.dp, top = 5.dp)
                )
                OutlinedTextField(
                    value = verifyPassword.value ,
                    onValueChange ={
                                   verifyPassword.value = it
                    },
                    placeholder = {
                        Text(text = "Re-type password here",
                            fontSize = 12.sp)
                    },
                    //styling

                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .height(55.dp)
                        .border(
                            width = .1.dp,
                            color = Color(0xFF2F4858),
                            shape = RoundedCornerShape(15.dp)
                        )
                        .background(Color(0xFFFAFAFA))
                )

//Radio button row manager
Row(
    horizontalArrangement = Arrangement.Start,
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
        .fillMaxWidth(.98f)
        .wrapContentHeight(Alignment.CenterVertically)
){
    //Business part
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(end = 1.dp)
    ) {
        RadioButton(
            selected = selectedBusiness.value == 1,
            onClick = {
                selectedBusiness.value = 1
            })
        Text(text = "Business",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF2F4858)
            ),
            modifier = Modifier.align(Alignment.CenterVertically)
            )
    }
    //Individual part
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        RadioButton(
            selected = selectedBusiness.value == 0,
            onClick = {
                selectedBusiness.value = 0
        })
        Text(text = "Individual"
        ,   style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF2F4858)
            ),
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .wrapContentWidth()
            )
    }
}

                Button(
                    onClick = {
                       navigator.push(HomeScreen())
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6FC07A)
                    ),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .height(55.dp)
                        .padding(bottom = 5.dp)

                ) {
                    Text(
                        text = "Register",
                        modifier = Modifier
                            .wrapContentSize(Alignment.Center)
                            .align(Alignment.CenterVertically),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFAFAFA),
                            textAlign = TextAlign.Center,
                        ))
                }

                Text(
                    text = "Having an account?",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF2F4858)
                    ),
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .padding(
                            bottom = 5.dp,
                            start = 5.dp
                        )
                )

                Button(
                    onClick = {
                        navigator.push(LoginScreen())
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2F4858)
                    ),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .height(50.dp)

                ) {
                    Text(
                        text = "Login",
                        modifier = Modifier
                            .wrapContentSize(Alignment.Center)
                            .align(Alignment.CenterVertically),
                        style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFFFAFAFA),

                        textAlign = TextAlign.Center,
                    ))
                }

            }




        }
    }




