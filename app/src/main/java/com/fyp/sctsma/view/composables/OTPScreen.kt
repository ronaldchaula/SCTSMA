package com.fyp.sctsma.view.composables

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fyp.sctsma.R
import com.fyp.sctsma.view.composables.commonComponents.CircularProgressDisplay
import com.fyp.sctsma.view.composables.commonComponents.OTPMessage
import com.fyp.sctsma.view.composables.commonComponents.ValidationErrorMessage
import com.fyp.sctsma.view.composables.navigation.ExternalRoutes
import com.fyp.sctsma.view.composables.navigation.InternalRoutes
import com.fyp.sctsma.viewmodel.OtpViewModel

@Composable
fun OTPScreen(otp:String?, navController: NavController){

        //navigation
        val context = LocalContext.current
    //view model object
       val otpViewModel = remember{ OtpViewModel() }
        //hoisting the states
        var otpEntry by remember { mutableStateOf("") } //otp entry state
        val otp = otp


        //login state check
       val validationMessage by otpViewModel.errorMessage.observeAsState()
       val otpToastMessage by otpViewModel.otpData.observeAsState()
       val isLoading by otpViewModel.isLoading.observeAsState()
       val activationSuccess by otpViewModel.activationSuccess.observeAsState()

        //Central Arrangement
        BackgroundImage()
        Column(
            modifier = Modifier
                .fillMaxSize()
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        )
        {
            Spacer(modifier = Modifier.fillMaxHeight(.05f))
            CircularProgressDisplay(isLoading!!)
            //App title text
            Text(text = stringResource(
                R.string.app_name),
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .padding(bottom = 10.dp, top = 20.dp)
                ,
                style = TextStyle(
                    fontSize = 30.sp,
//                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFFFAFAFA),
                    textAlign = TextAlign.Center
                )

            )



            Text(text = stringResource(R.string.otp),
                modifier = Modifier
                    .width(294.dp)
                    .wrapContentSize(Alignment.CenterStart)
                    .padding(start = 8.dp, bottom = 52.dp),

                style = TextStyle(
                    fontSize = 28.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF2F4858),
                    textAlign = TextAlign.Start
                )

            )

            //Login form container

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .width(294.dp)
                    .height(365.dp)
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

               // Spacer(modifier = Modifier.height(10.dp))
                ValidationErrorMessage(validationMessage)

                    OTPMessage(otp!!)



                Spacer(modifier = Modifier.fillMaxHeight(.05f))

                Text(
                    text = "Activate Account with OTP",
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF2F4858)
                    ),
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .padding(bottom = 5.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = otpEntry ,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    maxLines = 1,
                    onValueChange = {
                        //state of the data is changing
                                    otpEntry = it
                        //state of the viewmodel data is also changing hence allowing a network call
                                    otpViewModel.otp.value = it
                    },
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp, // Adjust font size as needed
                        fontWeight = FontWeight.Bold ,
                        color = Color(0xFF2F4858)
                    ),
                    placeholder = {
                        Text(
                            text = "____-_____-____-_____-____-____",
                            textAlign = TextAlign.Center

                        )
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

                Spacer(modifier = Modifier.height(10.dp))
                LaunchedEffect(key1 = activationSuccess) {
                    if (activationSuccess == true) {
                        Toast.makeText(context,"$otpToastMessage",Toast.LENGTH_SHORT).show()
                        navController.popBackStack(InternalRoutes.HomeScreen.route,inclusive = true)
                        navController.navigate(ExternalRoutes.LoginScreen.route)

                    // Navigate to LoginScreen
                    }
                }
                Button(
                    onClick ={
// we call the method from here and pass in the data to be updated
                             otpViewModel.activateAccount()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6FC07A)
                    ),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .height(65.dp)
                        .padding(top = 10.dp)
                ) {
                    Text(text = "ACTIVATE ACCOUNT",
                        modifier = Modifier.wrapContentSize(Alignment.Center),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFAFAFA),
                            textAlign = TextAlign.Center,
                        ))
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                        navController.popBackStack()

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2F4858)
                    ),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .height(55.dp)

                ) {
                    Text(
                        text = "BACK",
                        modifier = Modifier.wrapContentSize(Alignment.Center),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFAFAFA),

                            textAlign = TextAlign.Center,
                        ))
                }

            }




        }

    }
@Preview
@Composable
fun OTPScreenPreview(){
    OTPScreen("", rememberNavController())
}





