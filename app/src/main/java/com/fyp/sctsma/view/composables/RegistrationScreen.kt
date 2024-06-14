package com.fyp.sctsma.view.composables

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.fyp.sctsma.R
import com.fyp.sctsma.viewmodel.RegisterViewModel

class RegistrationScreen : Screen{
    @Composable
    override fun Content() {

        val registerViewModel = rememberScreenModel {RegisterViewModel()}
        val context = LocalContext.current
        //phone and email regex
        Regex("^(\\+255)?\\d{10,}\$")
        //account type
        val options = listOf("Business", "Individual")
        val selectedOption = remember { mutableStateOf(options[0]) }

        val registrationNavigator = LocalNavigator.currentOrThrow

        //  Input state holders
        val verticalScrollState = rememberScrollState()

        val phoneNumber = remember { mutableStateOf("")}

        val password = remember { mutableStateOf("") }
        val confirmPassword = remember { mutableStateOf("") }

        //border colors states
        val phoneNumberBorderColor = remember { mutableStateOf(Color(0xFF2F4858)) }
        val passwordBorderColor = remember { mutableStateOf(Color(0xFF2F4858)) }
        val confirmPasswordBorderColor = remember { mutableStateOf(Color(0xFF2F4858)) }

        //boolean state on password states
        val passwordVisibility = remember { mutableStateOf(false) }
        val confirmPasswordVisibility = remember{ mutableStateOf(false) }

        //ViewModelObservations
        val registrationSuccess by registerViewModel.registrationSuccess.observeAsState()
        val validationMessage by registerViewModel.errorMessage.observeAsState()
        val receivedOTP by registerViewModel.obtainedOTP.observeAsState()
        val otpToDisplay = receivedOTP ?: ""
       Register(
           registrationNavigator,
           verticalScrollState,
           phoneNumber.value,
           phoneNumberBorderColor.value,
           {

            phoneNumber.value = it
            registerViewModel.phoneNumber.value = it
           },
           password.value,
           passwordBorderColor.value
           ,{
            password.value =  it
            registerViewModel.password.value =  it
           },
           confirmPassword.value,
           confirmPasswordBorderColor.value,
           {
           confirmPassword.value =  it
           registerViewModel.confirmPassword.value =  it
           },
           registerViewModel,
           options,
           selectedOption,
           {
               selectedOption.value = it
               registerViewModel.accountType.value = it
           },
           passwordVisibility.value,
           {
               passwordVisibility.value = !passwordVisibility.value
           },
           confirmPasswordVisibility.value,
           {
               confirmPasswordVisibility.value = !confirmPasswordVisibility.value
           },
           validationMessage
           ,
           registrationSuccess,
           otp = otpToDisplay,
           context
           )
    }
}

@Composable
fun Register(
    navigator: Navigator = LocalNavigator.currentOrThrow,
    verticalScrollState: ScrollState,
    phoneNumber: String,
    phoneNumberBorderColor: Color,
    onContactPhoneChange: (String) -> Unit,
    password: String,
    passwordBorderColor: Color,
    onPasswordChange: (String) -> Unit,
    confirmPassword: String,
    confirmPasswordBorderColor: Color,
    onConfirmedPasswordChange: (String) -> Unit,
    registerViewModel: RegisterViewModel,
    options: List<String>,
    selectedOption: MutableState<String>,
    onOptionSelectedChange: (String) -> Unit,
    passwordVisibility: Boolean,
    onPasswordVisibilityChange: (Boolean) -> Unit,
    confirmPasswordVisibility: Boolean,
    onConfirmPasswordVisibilityChange: (Boolean) -> Unit,
    validationMessage: String?,
    registrationSuccess: Boolean?,
    otp:String,
    context: Context
) {

    //state management

//central container begins
    BackgroundImage()

    //top layer starts here
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
                .verticalScroll(verticalScrollState, enabled = true),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )

        {
            //App title text
            Text(text = stringResource(R.string.app_name),
                modifier = Modifier
                    .width(294.dp)
                    .wrapContentHeight()
                  ,
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFAFAFA),
                    textAlign = TextAlign.Center
                )

            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = stringResource(R.string.preliminary),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(
                        start = 8.dp,
                        bottom = 0.dp,
                        top = 0.dp
                    ),

                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF2F4858),
                    textAlign = TextAlign.Start
                )

            )

            Spacer(modifier = Modifier.height(20.dp))
            //Login form container

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
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
                if (validationMessage != null){
                    Column(
                        modifier = Modifier
                            .background(
                                Color.Red
                            )
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(top = 4.dp, bottom = 4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = validationMessage,
                            fontSize = 8.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            modifier = Modifier.padding(4.dp)
                        )
                    }

                }
                Spacer(modifier = Modifier.height(24.dp))



               PhoneNumberInput(
                   phoneNumber = phoneNumber,
                   onContactPhoneChange = onContactPhoneChange,
                   phoneNumberBorderColor
               )
                Spacer(modifier = Modifier.height(8.dp))
                PasswordInput(
                    password = password,
                    onPasswordChange = onPasswordChange,
                    passwordBorderColor,
                    passwordVisibility,
                    onPasswordVisibilityChange
                )
                Spacer(modifier = Modifier.height(8.dp))
                ConfirmedPasswordInput(
                    confirmPassword = confirmPassword,
                    onConfirmedPasswordChange = onConfirmedPasswordChange
                    ,
                    confirmPasswordBorderColor,
                    confirmPasswordVisibility,
                    onConfirmPasswordVisibilityChange
                )
                Spacer(modifier = Modifier.height(8.dp))
                //Radio button row manager
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth(.98f)
                        .wrapContentHeight(Alignment.CenterVertically)
                ){
                   AccountType(
                       options = options,
                       selectedOption = selectedOption,
                       onOptionSelectedChange = onOptionSelectedChange //{ selectedOption.value = it }
                   )
                }
                RegisterButton(registerViewModel)

                LaunchedEffect(key1 = registrationSuccess) {
                    if (registrationSuccess == true) {
                        Toast.makeText(
                            context,
                            "Accounted Created Successfully",
                            Toast.LENGTH_SHORT
                        ).show()

                        navigator.push(OTPScreen(otp)) // Push OTP screen onto stack
                    }
                    }
                Spacer(modifier = Modifier.height(2.dp))
                MessageLabel()
                Spacer(modifier = Modifier.height(2.dp))
                LoginScreenRedirectButton(navigator)
                Spacer(modifier = Modifier.height(20.dp))
                }


            }




        }




@Composable
private fun MessageLabel() {
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
}

@Composable
private fun LoginScreenRedirectButton(navigator: Navigator) {
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
            )
        )
    }
}







@Composable
 fun PhoneNumberInput(
    phoneNumber: String,
    onContactPhoneChange: (String) -> Unit,
    phoneNumberBorderColor: Color
) {
    Text(
        text = "Phone Number",
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF2F4858)
        ),
        modifier = Modifier
            .fillMaxWidth(.9f)
            .padding(bottom = 2.5.dp)
    )

    //phone input
    OutlinedTextField(
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        value = phoneNumber,
        onValueChange = onContactPhoneChange,
        placeholder = {
            Text(
                text = "255-XXX-XXXXXXX",
                fontSize = 14.sp
            )
        },
        //styling

        textStyle = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF2F4858)
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth(.9f)
            .height(50.dp)
            .border(
                width = 1.dp,
                color = phoneNumberBorderColor,
                shape = RoundedCornerShape(15.dp)
            )
            .background(Color(0xFFFAFAFA))
    )
}

@Composable
fun PasswordInput(
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordBorderColor: Color,
    passwordVisibility: Boolean,
    onPasswordVisibilityChange: (Boolean) -> Unit
    ) {
    Text(
        text = "Password",
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF2F4858)
        ),
        modifier = Modifier
            .fillMaxWidth(.9f)
            .padding(bottom = 2.5.dp, top = 2.5.dp)
    )

    //Password
    OutlinedTextField(
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(
                onClick = { onPasswordVisibilityChange(!passwordVisibility) }
            ) {
                Icon(
                    imageVector = if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = if (passwordVisibility) "Hide password" else "Show password"
                )
            }
        },
        value = password,
        onValueChange = onPasswordChange,
        placeholder = {
            Text(
                text = "xxxxxxxxxx",
                fontSize = 14.sp
            )
        },
        //styling
        textStyle = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF2F4858)
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth(.9f)
            .height(50.dp)
            .border(
                width = .1.dp,
                color = passwordBorderColor,
                shape = RoundedCornerShape(15.dp)
            )
            .background(Color(0xFFFAFAFA))
    )
}
@Composable
fun ConfirmedPasswordInput(
    confirmPassword: String,
    onConfirmedPasswordChange: (String) -> Unit,
    confirmPasswordBorderColor: Color,
    confirmPasswordVisibility: Boolean,
    onConfirmPasswordVisibilityChange: (Boolean) -> Unit
) {

    Text(
        text = "confirm Password",
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF2F4858)
        ),
        modifier = Modifier
            .fillMaxWidth(.9f)
            .padding(bottom = 2.5.dp, top = 2.5.dp)
    )
    OutlinedTextField(
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (confirmPasswordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(
                onClick =  {onConfirmPasswordVisibilityChange(!confirmPasswordVisibility) }
            ) {
                Icon(
                    imageVector = if (confirmPasswordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = if (confirmPasswordVisibility) "Hide password" else "Show password"
                )
            }
        },
       textStyle = TextStyle(
           fontSize = 14.sp,
           fontWeight = FontWeight(500),
           color = Color(0xFF2F4858)
       ),
        value = confirmPassword,
        onValueChange = onConfirmedPasswordChange,
        placeholder = {
            Text(
                text = "xxxxxxxxxx",
                fontSize = 14.sp
            )
        },
        //styling

        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth(.9f)
            .height(50.dp)
            .border(
                width = .1.dp,
                color = confirmPasswordBorderColor,
                shape = RoundedCornerShape(15.dp)
            )
            .background(Color(0xFFFAFAFA))
    )
}

@Composable
private fun RegisterButton(registerViewModel: RegisterViewModel) {
    Button(
        onClick = {
          registerViewModel.registerUser()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF6FC07A)
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth(.9f)
            .height(50.dp)
            .padding(bottom = 2.5.dp)

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
            )
        )
    }
}

//@Composable
//@Preview
//fun RegistrationScreenPreview(){
//    val options = listOf("Business", "Individual")
//    //val selectedOption = remember { mutableStateOf(options[0]) }
//    Register(
//        navigator = LocalNavigator.currentOrThrow,
//        verticalScrollState = rememberScrollState(),
//        phoneNumber = "",
//        phoneNumberBorderColor = Color(0xFF2F4858),
//        onContactPhoneChange = {},
//        password = "",
//        passwordBorderColor = Color(0xFF2F4858),
//        onPasswordChange = {},
//        confirmPassword = "",
//        confirmPasswordBorderColor = Color(0xFF2F4858),
//        onConfirmedPasswordChange = {},
//        registerViewModel = RegisterViewModel(),
//        options = listOf("Business", "Individual"),
//        selectedOption = remember { mutableStateOf(options[0]) },
//        onOptionSelectedChange = {},
//        passwordVisibility = false,
//        onPasswordVisibilityChange = {},
//        confirmPasswordVisibility = false,
//        onConfirmPasswordVisibilityChange = {},
//        validationMessage = null,
//        registrationSuccess = false,
//        otp = "",
//        context = LocalContext.current
//
//    )
//}