package com.fyp.sctsma.view.composables

import android.content.Context
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fyp.sctsma.R
import com.fyp.sctsma.view.composables.commonComponents.CircularProgressDisplay
import com.fyp.sctsma.view.composables.commonComponents.PasswordInput
import com.fyp.sctsma.view.composables.commonComponents.PhoneNumberInput
import com.fyp.sctsma.view.composables.commonComponents.ValidationErrorMessage
import com.fyp.sctsma.view.composables.navigation.ExternalRoutes
import com.fyp.sctsma.viewmodel.LoginViewModel

@Composable
fun LoginScreen(initialNavController: NavHostController) {
        val context = LocalContext.current
        //navigation component
        val navigationController = initialNavController
        //view model object
         val loginViewModel = remember{LoginViewModel(context)}
        //hoisting the states
        var phoneNumber by remember { mutableStateOf("") } //this remembers the phone input
        var password by remember { mutableStateOf("") } //this remembers the password input

        //login state check
        val validationMessage by loginViewModel.errorMessage.observeAsState()
        val username by loginViewModel.username.observeAsState()
        val loginSuccess by loginViewModel.loginSuccess.observeAsState()
        val isLoading by loginViewModel.isLoading.observeAsState()

        // password Visibility state
        var passwordVisibility by remember { mutableStateOf(false) }
        //waiting progress icon

     Login(
         //passing the states
         navigationController,
         phoneNumber = phoneNumber,
         onUserPhoneUpdated = { newPhoneNumber ->
             phoneNumber = newPhoneNumber
             loginViewModel.phoneNumber.value = newPhoneNumber
         },
         password = password,
         onPasswordUpdated = {
             newPassword -> password = newPassword
             loginViewModel.password.value = newPassword
         },
         loginViewModel = loginViewModel,
         loginSuccess = loginSuccess!!,
         isLoading = isLoading!!,
         context = context,
         validationMessage,
         passwordVisibility = passwordVisibility,
         onPasswordVisibilityChange = {
             passwordVisibility = it
         }
         ,
         username
         )

    }


@Composable
fun Login(
    navigationController: NavHostController,
    phoneNumber: String,
    onUserPhoneUpdated: (String) -> Unit,
    password: String,
    onPasswordUpdated: (String) -> Unit,
    loginViewModel: LoginViewModel,
    loginSuccess: Boolean,
    isLoading: Boolean,
    context: Context = LocalContext.current,
    validationMessage: String?,
    passwordVisibility: Boolean,
    onPasswordVisibilityChange: (Boolean) -> Unit,
    username: String?
) {

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
            //loading icon
            CircularProgressDisplay(isLoading)



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

            //Login page heading 2

            Text(text = stringResource(R.string.login_heading),
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
                    .wrapContentHeight()
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
                ValidationErrorMessage(validationMessage)

                Spacer(modifier = Modifier.fillMaxHeight(.01f))
//PhoneNumberInput has its own title and input capabilities you just need to use it and pass it necessary params
                PhoneNumberInput(
                    phoneNumber = phoneNumber,
                    onContactPhoneChange = onUserPhoneUpdated,
                    phoneNumberBorderColor = Color(0xFF2F4858),
                )
                PasswordInput(
                    password = password,
                    onPasswordChange = onPasswordUpdated,
                    passwordBorderColor = Color(0xFF2F4858),
                    passwordVisibility = passwordVisibility,
                    onPasswordVisibilityChange = onPasswordVisibilityChange
                )
                //Password

                Button(
                    onClick ={
                             loginViewModel.loginUser()
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
                    Text(text = "Login",
                        modifier = Modifier.wrapContentSize(Alignment.Center),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFAFAFA),
                            textAlign = TextAlign.Center,
                        ))
                }

                LaunchedEffect(key1 = loginSuccess) {
                    if (loginSuccess) {
                        navigationController.popBackStack(0, true)
                        navigationController.navigate(ExternalRoutes.HomeRoute.route)
                    }
                }


                Text(
                    text = "Don’t have an account?",
                    style = TextStyle(
                        fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF2F4858)
                    ),
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .padding(start = 4.dp, bottom = 5.dp, top = 5.dp)
                )

                Button(
                    onClick = {
                        navigationController.navigate(ExternalRoutes.RegisterScreen.route)
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
                        text = "Register",
                        modifier = Modifier.wrapContentSize(Alignment.Center),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFAFAFA),

                            textAlign = TextAlign.Center,
                        ))
                }
                Spacer(modifier = Modifier.height(20.dp))

            }




        }
    }


@Preview
@Composable
fun LoginPreview(){
    LoginScreen(rememberNavController())
}

