package com.fyp.sctsma.view.composables

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ScrollState
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fyp.sctsma.view.composables.commonComponents.CircularProgressDisplay
import com.fyp.sctsma.view.composables.commonComponents.NavigationPanel
import com.fyp.sctsma.view.composables.commonComponents.ValidationErrorMessage
import com.fyp.sctsma.view.composables.navigation.InternalRoutes
import com.fyp.sctsma.viewmodel.SharedViewModel

@Composable
fun CreateOrdersScreen(navController: NavController) {

        val context = LocalContext.current

    //sharedViewModel
        val sharedViewModel = remember { SharedViewModel(context) }

        //state holders
        val  orderTitle = remember { mutableStateOf("")}
        val  orderQty = remember { mutableStateOf("")}
        val  orderDescription = remember { mutableStateOf("")}
        val  orderCost = remember { mutableStateOf("")}
        val  lipaNumber = remember { mutableStateOf("")}
        //observe entries
        val validationMessage by sharedViewModel.error.observeAsState()
        val success by sharedViewModel.success.observeAsState()
        val isLoading by sharedViewModel.loading.observeAsState()
        val scrollState = rememberScrollState()
        //this will run only when the required condition is true

    //on success network call check if there is an error, if not proceed to home
        LaunchedEffect(key1 = true) {
            if (success == true) {
                Toast.makeText(context,"Order Created Successfully", Toast.LENGTH_SHORT).show()
                navController.navigate(InternalRoutes.HomeScreen.route)

            }else if(validationMessage?.isNotEmpty() == true){
                scrollState.scrollTo(0)
            }
        }

    CreateOrder(
        orderTitle = orderTitle.value,
        onOrderTitleChange = {
            orderTitle.value = it
            sharedViewModel.orderTitle.value = it
        },
        orderQty = orderQty.value,
        onOrderQuantityChange = {
            orderQty.value = it
            sharedViewModel.orderQty.value = it
        },
        orderDescription = orderDescription.value,
        onOrderDescriptionChange = {
            orderDescription.value = it
            sharedViewModel.orderDescription.value = it
        },
        orderCost = orderCost.value,
        onOrderCostChange = {
            orderCost.value = it
            sharedViewModel.orderCost.value = it
        },
        lipaNumber = lipaNumber.value,
        onLipaNumberChange = {
            lipaNumber.value = it
            sharedViewModel.lipaNumber.value = it
        },
        validationMessage = validationMessage ?: "",
        isLoading = isLoading!!,
        success = success!!,
        onOrderSubmission = {
            sharedViewModel.postOrder()
        },
        context = context,
        navigator = navController,
        scrollState = scrollState
    )
    }


@Composable
fun CreateOrder(
    orderTitle: String,
    onOrderTitleChange: (String) -> Unit,
    orderQty: String,
    onOrderQuantityChange: (String) -> Unit,
    orderDescription: String,
    onOrderDescriptionChange: (String) -> Unit,
    orderCost: String,
    onOrderCostChange: (String) -> Unit,
    lipaNumber: String,
    onLipaNumberChange: (String) -> Unit,
    validationMessage: String,
    isLoading: Boolean,
    success: Boolean,
    onOrderSubmission: () -> Unit = {},
    context: Context,
    navigator: NavController,
    scrollState: ScrollState
){

    Box(
        modifier = Modifier.fillMaxSize()

    ){
        //everything lies on top of the next composable
        BackgroundImage()
        //vertical arrangement component
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()

        ) {

            NavigationPanel("Create Order", navController = navigator)

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(15.dp))
                    .fillMaxWidth(.9f)
                    .fillMaxHeight(.98f)
                    .verticalScroll(scrollState)
                    .background(
                        color = Color(0xFAFAFAFA)
                    )
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

            ) {
                //whole scope

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                    ,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)

                    ) {

                        if (validationMessage.isNotEmpty()){
                            ValidationErrorMessage(validationMessage = validationMessage)}
                    }
                    Spacer(modifier = Modifier.height(1.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth(.9f)
                            .height(75.dp)

                    ) {

                        OrderTitleComponent(
                            modifier = Modifier
                            ,
                            orderTitle = orderTitle,
                            onOrderTitleChange = onOrderTitleChange
                        )
                        OrderQuantityComponent(
                            modifier = Modifier
                            ,
                            orderQty = orderQty,
                            onOrderQuantityChange = onOrderQuantityChange
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    OrderDescription(
                        modifier = Modifier
                        ,
                        orderDescription = orderDescription,
                        onOrderDescriptionChange = onOrderDescriptionChange
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    OrderCost(
                        modifier = Modifier,
                        orderCost = orderCost,
                        onOrderCostChange = onOrderCostChange
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    LipaNamba(
                        modifier =  Modifier,
                        lipaNumber = lipaNumber,
                        onLipaNumberChange = onLipaNumberChange
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    CreateOrderButton(
                        modifier = Modifier,
                        onOrderSubmission = onOrderSubmission
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    LaunchedEffect(key1 = success) {
                        if (success) {
                            Toast.makeText(context,
                                "Order Created Successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                            navigator.navigate(InternalRoutes.HomeScreen.route)
                        }

                    }

                }



            }
        }

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.2f)), // Semi-transparent background
                contentAlignment = Alignment.Center
            ) {
                CircularProgressDisplay(isLoading = true, color = Color(0xFF6FC07A))
            }
        }

    }
}








@Composable
fun CreateOrderButton(
    onOrderSubmission: () -> Unit,
    modifier: Modifier,
) {
    Button(
        onClick = onOrderSubmission,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF6FC07A)
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = modifier
            .fillMaxWidth(.9f)
            .height(55.dp)
    ) {
        Text(text = "Create Order",
            modifier = modifier.wrapContentSize(Alignment.Center),
            style = TextStyle(
                fontSize = 16.sp,
//                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontWeight = FontWeight(600),
                color = Color(0xFFFAFAFA),
                textAlign = TextAlign.Center,
            )
        )
    }
}

@Composable
fun LipaNamba(
    lipaNumber: String = "",
    onLipaNumberChange: (String) -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(.9f)
            .height(90.dp)
    ) {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 2.5.dp),
            text = "SCTSMA Lipa Number",
            fontSize = 12.5.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF2F4858),
        )

        OutlinedTextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF2F4858),
                textAlign = TextAlign.Start
            ),
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp)
                ,
            shape = RoundedCornerShape(15.dp),
            value = lipaNumber,
            onValueChange = onLipaNumberChange,
            colors = OutlinedTextFieldDefaults.colors(
                //unfocused 0xFF2F4858
                //focused 0xFF6FC07A
                focusedBorderColor = Color(0xFF6FC07A),
                unfocusedBorderColor = Color(0xFF2F4858),
                disabledBorderColor = Color(0x33000000)
            )
        )
    }
}


@Composable
fun OrderCost(
    orderCost: String = "",
    onOrderCostChange: (String) -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(.9f)
    ) {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 2.5.dp)
                ,
            text = "Amount in Tsh",
            fontSize = 12.5.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF2F4858),
        )

        OutlinedTextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF2F4858),
            ),
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(15.dp),
            value = orderCost,
            onValueChange = onOrderCostChange,
            colors = OutlinedTextFieldDefaults.colors(
                //unfocused 0xFF2F4858
                //focused 0xFF6FC07A
                focusedBorderColor = Color(0xFF6FC07A),
                unfocusedBorderColor = Color(0xFF2F4858),
                disabledBorderColor = Color(0x33000000)
            )
        )
    }
}
@Composable
fun OrderDescription(
    orderDescription: String,
    onOrderDescriptionChange: (String) -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(.9f)
            .height(205.dp)
    ) {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 2.5.dp)
            ,
            text = "Order Description",
            fontSize = 12.5.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF2F4858),
        )
        OutlinedTextField(
            textStyle = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF2F4858),
            ),
            singleLine = false,
            maxLines = 100,
            minLines = 100,
            shape = RoundedCornerShape(15.dp),
            modifier = modifier.fillMaxSize(),
            value = orderDescription,
            onValueChange = onOrderDescriptionChange,
            colors = OutlinedTextFieldDefaults.colors(
                //unfocused 0xFF2F4858
                //focused 0xFF6FC07A
                focusedBorderColor = Color(0xFF6FC07A),
                unfocusedBorderColor = Color(0xFF2F4858),
                disabledBorderColor = Color(0x33000000)
            )
        )
    }
}

@Composable
fun OrderTitleComponent(
    orderTitle: String = "",
    onOrderTitleChange: (String) -> Unit,
    modifier: Modifier
) {
    Column(
modifier = modifier
    .fillMaxWidth(.7f)
    .height(90.dp)

    ) {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 2.5.dp),
            text = "Title",
            fontSize = 12.5.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF2F4858),
        )
        OutlinedTextField(
            textStyle = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF2F4858),
            ),
            singleLine = true,
            maxLines = 1,
            shape = RoundedCornerShape(15.dp),

            modifier = modifier
                .fillMaxWidth()
                .height(50.dp)
                ,
            value = orderTitle,
            onValueChange = onOrderTitleChange,
            colors = OutlinedTextFieldDefaults.colors(
                //unfocused 0xFF2F4858
                //focused 0xFF6FC07A
                focusedBorderColor = Color(0xFF6FC07A),
                unfocusedBorderColor = Color(0xFF2F4858),
                disabledBorderColor = Color(0x33000000)
            )
        )
    }
}

@Composable
fun  OrderQuantityComponent(
    orderQty: String = "",
    onOrderQuantityChange: (String) -> Unit,
    modifier: Modifier
){
    Column(
modifier = modifier
    .width(70.dp)
    ) {
        Text(

            textAlign = TextAlign.Center,
            text = "Quantity",
            fontSize = 12.5.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF2F4858),
            modifier = Modifier
                .padding(bottom = 2.5.dp)

            )

        OutlinedTextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF2F4858),
            ),
            singleLine = true,
            maxLines = 1,
            shape = RoundedCornerShape(15.dp),

            modifier = modifier
                .fillMaxWidth()
                .height(50.dp),
            value = orderQty,
            onValueChange = onOrderQuantityChange,
            colors = OutlinedTextFieldDefaults.colors(
                //unfocused 0xFF2F4858
                //focused 0xFF6FC07A
                focusedBorderColor = Color(0xFF6FC07A),
                unfocusedBorderColor = Color(0xFF2F4858),
                disabledBorderColor = Color(0x33000000)
            )
        )

    }
}




@Preview
@Composable
fun CreateOrderPreview(){
    CreateOrder(
        orderTitle = "",
        onOrderTitleChange = {},
        orderQty = "",
        onOrderQuantityChange = {},
        orderDescription = "",
        onOrderDescriptionChange = {},
        orderCost = "",
        onOrderCostChange = {},
        lipaNumber = "",
        onLipaNumberChange = {},
        validationMessage = "",
        isLoading = false,
        success = false,
        onOrderSubmission = {},
        context = LocalContext.current,
        navigator = rememberNavController(),
        scrollState = rememberScrollState()
    )
}
